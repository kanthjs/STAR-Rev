suppressMessages(library(STAR))
t <- function(name, expr) {
  out <- tryCatch({ capture.output(force(expr)); "OK" }, error = function(e) paste("ERROR:", conditionMessage(e)),
                  warning = function(w) paste("WARN:", conditionMessage(w)))
  cat(sprintf("%-26s %s\n", name, out))
}
data(Fertilizer); data(GomezSplitRCBD); data(Maize); data(Yield94)
dr <- Fertilizer; dd <- GomezSplitRCBD; yy <- Yield94; mm <- Maize
t("DescriptiveStatistics", DescriptiveStatistics(data="dr", var="NumFruit", grp="Fertilizer", statistics=c("nnmiss","mean","sd","min","max")))
t("CompareMeans indep", CompareMeans(data="dr", varX="NumFruit", grp="Fertilizer", procedure="independent"))
t("NormalityTest", NormalityTest(data="dr", var="NumFruit", method="swilk"))
t("Crosstab", Crosstab(data="dd", rowVar="Nitrogen", colVar="Variety", chisq=TRUE))
t("AOVTest RCBD", AOVTest(data="dd", design="RCBD", respvar="Yield", factor1="Nitrogen", rep1="Rep", pwTest="LSD", pwVar="Nitrogen"))
t("AOVTest Split-RCBD", AOVTest(data="dd", design="SplitRCBD", respvar="Yield", factor1="Nitrogen", factor2="Variety", rep1="Rep", pwTest="Tukey", pwVar="Nitrogen"))
t("AOVTest LSD(Maize)", AOVTest(data="mm", design="LSD", respvar="GRAINYIELD", factor1="MAIZE", rep1="ROW", rep2="COLUMN"))
t("LinearRegression", { rd <- data.frame(y = rnorm(40), x1 = rnorm(40), x2 = rnorm(40)); LinearRegressionAnalysis(data="rd", depVar="y", indepVar=c("x1","x2")) })
t("BivariateCorrelation", { cd <- data.frame(a=rnorm(30), b=rnorm(30)); BivariateCorrelationTest(data="cd", var=c("a","b")) })
t("PCA", { pd <- as.data.frame(matrix(rnorm(200), 40)); PCA(data="pd", var=names(pd), scatterMatx=FALSE, scree=FALSE, biplot=FALSE, pcaplot=FALSE, saveScore=FALSE) })
t("ClusterKmeans", { pd <- as.data.frame(matrix(rnorm(200), 40)); ClusterKmeans(data="pd", var=names(pd), kgraph=FALSE, saveMem=FALSE) })
