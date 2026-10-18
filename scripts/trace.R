suppressMessages(library(STAR))
data(Fertilizer); data(Yield94); dr <- Fertilizer; yr <- Yield94[,c("Yield","Year")]; pd <- as.data.frame(matrix(rnorm(200), 40))
tr <- function(label, expr) {
  cat("\n###", label, "\n")
  withCallingHandlers(tryCatch(expr, error = function(e) cat("ERR:", conditionMessage(e), "\n")),
    error = function(e) { cs <- sys.calls(); cat(paste(vapply(tail(cs, 8), function(x) substr(paste(deparse(x), collapse = " "), 1, 130), ""), collapse = "\n"), "\n") })
}
tr("Normality", NormalityTest(data = "dr", var = "NumFruit", method = "swilk"))
tr("LinReg", LinearRegressionAnalysis(data = "yr", depVar = "Yield", indepVar = "Year"))
tr("PCA", PCA(data = "pd", var = names(pd), scatterMatx = FALSE, scree = FALSE, biplot = FALSE, pcaplot = FALSE, saveScore = FALSE))
