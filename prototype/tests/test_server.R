# Headless test of the app's server logic (native R). Run from anywhere: Rscript prototype/tests/test_server.R
here <- dirname(normalizePath(sub("--file=", "", grep("--file=", commandArgs(FALSE), value = TRUE))))
setwd(file.path(here, "..", "app"))
suppressMessages(library(shiny))
app <- source("app.R")$value
testServer(app, {
  session$setInputs(src = "ex")
  for (pw in c("", "LSD", "HSD", "duncan", "SNK", "scheffe")) {
    session$setInputs(resp = "Yield", trt = "Nitrogen", blk = "Rep", pw = pw, go_aov = which(c("", "LSD", "HSD", "duncan", "SNK", "scheffe") == pw))
    txt <- strsplit(aov_text(), "\n")[[1]]
    title <- grep("Test$|Difference|Range|DMRT", txt, value = TRUE)
    cat(sprintf("%-8s F=8.54:%s  %s\n", if (nzchar(pw)) pw else "auto", any(grepl("8.54", txt)), paste(trimws(title), collapse = " | ")))
  }
  session$setInputs(cp_aov = 1)
  cat("copy button ran without error\n")
})
