# Headless test of the app's server logic (native R). Run from anywhere: Rscript prototype/tests/test_server.R
here <- dirname(normalizePath(sub("--file=", "", grep("--file=", commandArgs(FALSE), value = TRUE))))
setwd(file.path(here, "..", "app"))
suppressMessages(library(shiny))
app <- source("app.R")$value
check <- function(cond, msg) if (!isTRUE(cond)) stop("FAILED: ", msg, call. = FALSE) else cat("ok  ", msg, "\n")

testServer(app, {
  n <- 0
  run <- function(...) { n <<- n + 1; session$setInputs(..., go_aov = n) }
  session$setInputs(src = "ex", alpha = "0.05", pw = "auto")

  # ---- RCBD, one factor (rice yield vs nitrogen) ----
  session$setInputs(design = "RCBD", example = "rcbd1", resp = "Yield", trt = "Nitrogen", blk = "Rep")
  tests <- c(auto = "Tukeys", LSD = "Least Significant", HSD = "Tukeys", duncan = "Duncan", SNK = "Newman", scheffe = "Scheffe")
  for (pw in names(tests)) { run(pw = pw); check(any(grepl(tests[[pw]], ok()$report)), paste("RCBD 1 factor: mean comparison", pw, "is used")) }
  run(pw = "LSD")
  check(round(ok()$anova$F[2], 2) == 8.54, "RCBD 1 factor: F = 8.54")
  check(identical(tables()$anova$`Pr(>F)`[2], "0.0022"), "RCBD 1 factor: p formatted 0.0022")
  check(identical(tables()$means$Nitrogen$Group, c("b", "a", "a", "a", "a", "a")), "RCBD 1 factor: letters")
  check(identical(tables()$means$Nitrogen$N, rep("3", 6)), "RCBD 1 factor: N as integers")

  # ---- CRD, one factor (insecticides) ----
  session$setInputs(design = "CRD", example = "crd1", resp = "GrainYield", trt = "Insecticide")
  run(pw = "HSD")
  check(is.null(ok()$block) && round(ok()$anova$F[1], 2) == 9.83, "CRD 1 factor: no block, F = 9.83")
  check(identical(tables()$means$Insecticide$Group, c("ab", "c", "a", "bc", "bc", "ab", "a")), "CRD 1 factor: letters match the original")

  # ---- CRD, two factors (tooth growth) ----
  session$setInputs(design = "CRD", example = "crd2", resp = "len", trt = c("supp", "dose"))
  run(pw = "auto")
  r <- ok()
  check(identical(r$anova$Source, c("supp", "dose", "supp:dose", "Error", "Total")), "CRD 2 factors: ANOVA rows")
  check(r$interaction_significant, "CRD 2 factors: interaction significant")
  check(setequal(names(tables()$simple), c("supp within dose", "dose within supp")), "CRD 2 factors: simple-effect tables")
  check(identical(names(tables()$cells), c("supp", "dose = 0.5", "dose = 1", "dose = 2")), "CRD 2 factors: wide cell means")
  check(identical(tables()$simple[["supp within dose"]][["dose = 0.5"]], c("13.23 a", "7.98 b")), "CRD 2 factors: simple effect letters (original STAR)")
  check(!("Group" %in% names(tables()$means$supp)), "CRD 2 factors: no main-effect letters when the interaction is significant")

  # ---- RCBD, two factors (nitrogen x variety) ----
  session$setInputs(design = "RCBD", example = "rcbd2", resp = "Yield", trt = c("Nitrogen", "Variety"), blk = "Rep")
  run(pw = "auto")
  r <- ok()
  check(identical(r$anova$Source, c("Rep", "Nitrogen", "Variety", "Nitrogen:Variety", "Error", "Total")), "RCBD 2 factors: ANOVA rows")
  check(round(r$cv, 2) == 10.07, "RCBD 2 factors: CV = 10.07")
  check(identical(tables()$simple[["Nitrogen within Variety"]][["Variety = IR8"]][1:2], c("4,252.67 d", "5,672.00 c")), "RCBD 2 factors: Nitrogen within IR8 letters")
  check("Simple effects" %in% names(xlsx_sheets()), "RCBD 2 factors: simple effects in Excel")

  # ---- split plot in RCBD (nitrogen main plot x variety sub plot) ----
  session$setInputs(design = "SplitRCBD", example = "split1", resp = "Yield", trt_main = "Nitrogen", trt_sub = "Variety", blk = "Rep")
  run(pw = "auto")
  r <- ok()
  check(identical(r$anova$Source, c("Rep", "Nitrogen", "Error(a)", "Variety", "Nitrogen:Variety", "Error(b)", "Total")), "Split plot: ANOVA rows")
  check(round(c(r$cv_a, r$cv_b), 2)[2] == 10.79 && round(r$cv_a, 2) == 6.88, "Split plot: CV(a) = 6.88, CV(b) = 10.79")
  check(identical(tables()$simple[["Nitrogen within Variety"]][["Variety = IR8"]][1:2], c("4,252.67 d", "5,672.00 c")), "Split plot: Nitrogen within IR8 letters (original STAR)")
  check(length(output$report) > 0 && file.size(output$dl_xlsx) > 3000, "Split plot: report and Excel")
  session$setInputs(plot_effect = "interaction"); p0 <- output$dl_png; check(file.size(p0) > 5000, "Split plot: interaction png")

  # ---- downloads and copy (two-factor result is selected) ----
  session$setInputs(design = "RCBD", example = "rcbd2", resp = "Yield", trt = c("Nitrogen", "Variety"), blk = "Rep"); run(pw = "auto")
  x <- output$dl_xlsx; check(file.exists(x) && file.size(x) > 3000, "xlsx download created")
  sh <- openxlsx::getSheetNames(x); check(identical(sh, c("ANOVA", "Means", "Cell means", "Simple effects", "Info")), "xlsx sheets for a two-factor result")
  session$setInputs(plot_effect = "interaction"); p1 <- output$dl_png; check(file.exists(p1) && file.size(p1) > 5000, "png (interaction plot)")
  session$setInputs(plot_effect = "Nitrogen"); p2 <- output$dl_png; check(file.exists(p2) && file.size(p2) > 5000, "png (main effect plot)")
  t <- output$dl_txt; check(length(readLines(t)) > 40, "txt report download")
  session$setInputs(cp_anova = 1, cp_means = 1, cp_report = 1)
  cat("ok   copy buttons ran\n")

  session$setInputs(design = "CRD", example = "crd1", resp = "GrainYield", trt = "Insecticide"); run(pw = "LSD")
  x1 <- output$dl_xlsx; check(identical(openxlsx::getSheetNames(x1), c("ANOVA", "Means", "Info")), "xlsx sheets for a one-factor result")

  # ---- errors are reported, not thrown ----
  run(pw = "LSD", resp = "GrainYield", trt = "GrainYield")
  check(inherits(res(), "star_error"), "bad column choice is reported as an error")
})
