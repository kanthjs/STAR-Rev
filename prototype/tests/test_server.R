# Headless test of the app's server logic (native R). Run from anywhere: Rscript prototype/tests/test_server.R
here <- dirname(normalizePath(sub("--file=", "", grep("--file=", commandArgs(FALSE), value = TRUE))))
setwd(file.path(here, "..", "app"))
suppressMessages(library(shiny))
app <- source("app.R")$value
stopifnot_ <- function(cond, msg) if (!isTRUE(cond)) stop("FAILED: ", msg, call. = FALSE) else cat("ok  ", msg, "\n")

testServer(app, {
  n <- 0
  run <- function(...) { n <<- n + 1; session$setInputs(..., go_aov = n) }

  # ---- RCBD example (rice yield, 6 nitrogen levels x 3 reps) ----
  session$setInputs(design = "RCBD", src = "ex", resp = "Yield", trt = "Nitrogen", blk = "Rep", alpha = "0.05")
  tests <- c(auto = "Tukeys", LSD = "Least Significant", HSD = "Tukeys", duncan = "Duncan", SNK = "Newman", scheffe = "Scheffe")
  for (pw in names(tests)) {
    run(pw = pw)
    stopifnot_(any(grepl(tests[[pw]], ok()$report)), paste("RCBD: mean comparison", pw, "is used"))
  }
  run(pw = "LSD")
  stopifnot_(round(ok()$anova$F[2], 2) == 8.54, "RCBD: F value 8.54")
  stopifnot_(identical(anova_df()$`Pr(>F)`[2], "0.0022"), "RCBD: formatted p-value 0.0022")
  stopifnot_(identical(means_df()$Group, c("b", "a", "a", "a", "a", "a")), "RCBD: letters")
  stopifnot_(identical(means_df()$N, rep("3", 6)), "RCBD: N shown as integers")

  # ---- CRD example (7 insecticides x 4 reps) ----
  session$setInputs(design = "CRD", resp = "GrainYield", trt = "Insecticide")
  run(pw = "HSD")
  r <- ok()
  stopifnot_(identical(r$design, "CRD") && is.null(r$block), "CRD: no block used")
  stopifnot_(round(r$anova$F[1], 2) == 9.83 && round(r$cv, 2) == 15.09, "CRD: F 9.83, CV 15.09 (original STAR)")
  stopifnot_(identical(means_df()$Group, c("ab", "c", "a", "bc", "bc", "ab", "a")), "CRD: letters match the original")
  stopifnot_(identical(means_df()$Insecticide[1:2], c("Azodin", "Control")), "CRD: treatment names intact")

  # ---- downloads and copy ----
  x <- output$dl_xlsx; stopifnot_(file.exists(x) && file.size(x) > 3000, "xlsx download created")
  stopifnot_(identical(openxlsx::getSheetNames(x), c("ANOVA", "Means", "Info")), "xlsx sheets")
  info <- openxlsx::read.xlsx(x, "Info"); stopifnot_(info$Value[1] == "CRD" && info$Value[4] == "-", "xlsx info sheet (CRD, no block)")
  p <- output$dl_png; stopifnot_(file.exists(p) && file.size(p) > 5000, "png download created")
  t <- output$dl_txt; stopifnot_(length(readLines(t)) > 30, "txt report download")
  session$setInputs(cp_anova = 1, cp_means = 1, cp_report = 1)
  cat("ok   copy buttons ran\n")

  # ---- errors are reported, not thrown ----
  run(pw = "LSD", resp = "GrainYield", trt = "GrainYield")
  stopifnot_(inherits(res(), "star_error"), "bad column choice is reported as an error")
})
