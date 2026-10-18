suppressMessages(library(STAR))
fns <- c("DescriptiveStatistics","AOVTest","designRCBD","designCRD","designLSD","designBIBD","CompareMeans","NormalityTest","Crosstab","PCA","ClusterKmeans","LinearRegressionAnalysis","BivariateCorrelationTest","GraphHist")
for (f in fns) { cat("\n##", f, "\n"); print(args(get(f))) }
for (d in c("Fertilizer","GomezSplitRCBD","Maize","Yield94")) { data(list=d); cat("\n##",d,"\n"); str(get(d)) }
