suppressMessages(library(STAR))
data(Fertilizer); dr <- Fertilizer; pd <- as.data.frame(matrix(rnorm(200), 40))
options(error = function() { traceback(3); quit(status = 1) })
which <- commandArgs(TRUE)[1]
if (which == "n") NormalityTest(data = "dr", var = "NumFruit", method = "swilk")
if (which == "p") PCA(data = "pd", var = names(pd), scatterMatx = FALSE, scree = FALSE, biplot = FALSE, pcaplot = FALSE, saveScore = FALSE)
