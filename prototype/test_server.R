setwd("D:/GitHub/STAR-Rev/prototype/app")
suppressMessages(library(shiny))
app <- source("app.R")$value
testServer(app, {
  session$setInputs(trts = "A, B, C, D", reps = 3, seed = 1, go_rand = 1)
  r <- rand(); cat("rand ok:", is.null(r$error), " rows:", nrow(r$book), "\n")
  cat("layout_text lines:", length(strsplit(layout_text(), "\n")[[1]]), "\n")
  cat("book_tsv first line:", strsplit(book_tsv(), "\n")[[1]][1], "\n")
  f <- tempfile(); output$dl_book2; 
  session$setInputs(src = "ex")
  session$setInputs(resp = "Yield", trt = "Nitrogen", blk = "Rep", pw = "LSD", go_aov = 1)
  cat("aov_text lines:", length(strsplit(aov_text(), "\n")[[1]]), " has F=8.54:", grepl("8.54", aov_text()), "\n")
  session$setInputs(cp_aov = 1, cp_book = 1, cp_layout = 1)
  cat("copy buttons ran without error\n")
})
