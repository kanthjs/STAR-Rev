# Headless test of the app's server logic (native R). Run from anywhere: Rscript prototype/tests/test_server.R
here <- dirname(normalizePath(sub("--file=", "", grep("--file=", commandArgs(FALSE), value = TRUE))))
setwd(file.path(here, "..", "app"))
suppressMessages(library(shiny))
app <- source("app.R")$value
stopifnot_ <- function(cond, msg) if (!isTRUE(cond)) stop("FAILED: ", msg, call. = FALSE) else cat("ok  ", msg, "\n")

testServer(app, {
  session$setInputs(src = "ex", resp = "Yield", trt = "Nitrogen", blk = "Rep", alpha = "0.05")
  tests <- c(auto = "Tukeys", LSD = "Least Significant", HSD = "Tukeys", duncan = "Duncan", SNK = "Newman", scheffe = "Scheffe")
  i <- 0
  for (pw in names(tests)) {
    i <- i + 1
    session$setInputs(pw = pw, go_aov = i)
    r <- ok()
    stopifnot_(any(grepl(tests[[pw]], r$report)), paste("mean comparison", pw, "is used"))
  }
  session$setInputs(pw = "LSD", go_aov = 10)
  stopifnot_(round(ok()$anova$F[2], 2) == 8.54, "F value 8.54")
  stopifnot_(identical(anova_df()$`Pr(>F)`[2], "0.0022"), "formatted p-value 0.0022")
  stopifnot_(identical(means_df()$Group, c("b", "a", "a", "a", "a", "a")), "letters")
  stopifnot_(grepl("significant effect", ok()$summary), "summary sentence")

  x <- output$dl_xlsx; stopifnot_(file.exists(x) && file.size(x) > 3000, "xlsx download created")
  sheets <- openxlsx::getSheetNames(x); stopifnot_(identical(sheets, c("ANOVA", "Means", "Info")), "xlsx sheets")
  p <- output$dl_png; stopifnot_(file.exists(p) && file.size(p) > 5000, "png download created")
  t <- output$dl_txt; stopifnot_(length(readLines(t)) > 40, "txt report download")
  session$setInputs(cp_anova = 1, cp_means = 1, cp_report = 1)
  cat("ok   copy buttons ran\n")

  session$setInputs(resp = "Yield", trt = "Yield", blk = "Rep", go_aov = 11)
  stopifnot_(inherits(res(), "star_error"), "bad column choice is reported as an error")
})
