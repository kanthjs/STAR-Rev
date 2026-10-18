setwd("D:/GitHub/STAR-Rev/prototype/app")
suppressMessages(library(shiny))
app <- source("app.R")$value
testServer(app, {
  session$setInputs(src = "ex", resp = "Yield", trt = "Nitrogen", blk = "Rep")
  for (pw in c("", "LSD", "HSD", "duncan", "SNK", "scheffe")) {
    session$setInputs(pw = pw, go_aov = which(c("", "LSD", "HSD", "duncan", "SNK", "scheffe") == pw))
    txt <- aov_text()
    title <- grep("Test$|Difference|Range", strsplit(txt, "\n")[[1]], value = TRUE)
    cat(sprintf("%-8s -> %s\n", if (nzchar(pw)) pw else "auto", paste(trimws(title), collapse = " | ")))
  }
})
