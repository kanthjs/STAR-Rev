args <- commandArgs(TRUE)
base <- args[1]; out <- args[2]
e <- new.env()
lazyLoad(base, envir = e)
nm <- sort(ls(e, all.names = TRUE))
fn <- character(); other <- character()
for (n in nm) {
  o <- get(n, envir = e)
  if (is.function(o)) {
    fn <- c(fn, n)
    f <- file(file.path(out, paste0(gsub("[^A-Za-z0-9._-]", "_", n), ".R")), "w")
    writeLines(c(paste0("`", n, "` <-"), deparse(o, control = c("keepInteger","keepNA","quoteExpressions")) ), f)
    close(f)
  } else other <- c(other, n)
}
writeLines(fn, file.path(out, "_functions.txt"))
writeLines(other, file.path(out, "_nonfunctions.txt"))
cat(length(fn), "functions;", length(other), "other objects\n")
