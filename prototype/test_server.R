setwd("D:/GitHub/STAR-Rev/prototype/app")
suppressMessages(library(shiny))
app <- source("app.R")$value
testServer(app, {
  session$setInputs(trts = "A, B, C, D", reps = 3, seed = 1, go_rand = 1)
  r <- rand(); cat("rand error:", is.null(r$error), " rows:", nrow(r$book), "\n"); print(head(r$book, 4))
  session$setInputs(src = "ex")
  session$setInputs(resp = "Yield", trt = "Nitrogen", blk = "Rep", pw = "LSD", go_aov = 1)
  a <- aov_res(); cat("aov error:", if (is.null(a$error)) "none" else a$error, " lines:", length(a$text), "\n"); cat(head(a$text, 24), sep = "\n")
})
