# Usage: Rscript compare_cases.R <outdir>
# Runs the same deterministic STAR calls and stores the printed output of each case in <outdir>/<case>.txt
outdir <- commandArgs(TRUE)[1]
dir.create(outdir, recursive = TRUE, showWarnings = FALSE)
suppressMessages(library(STAR))
data(Fertilizer, GomezSplitRCBD, Maize, Yield94, CochranBIB, IASRILSD)
dr <- Fertilizer; dd <- GomezSplitRCBD; mm <- Maize; yy <- Yield94
ir <- iris; ir$Species <- as.factor(as.character(ir$Species))
mt <- mtcars; mt$cyl <- factor(mt$cyl); mt$am <- factor(mt$am)
tg <- ToothGrowth; tg$dose <- factor(tg$dose)
pg <- PlantGrowth

cases <- list(
  Descriptive      = quote(DescriptiveStatistics(data = "ir", var = c("Sepal.Length", "Petal.Width"), grp = "Species", statistics = c("nnmiss", "mean", "sd", "min", "max", "median", "cv", "skewness", "kurtosis"))),
  Descriptive_ng   = quote(DescriptiveStatistics(data = "mt", var = c("mpg", "hp"))),
  CompareMeans_one = quote(CompareMeans(data = "mt", varX = "mpg", testVal = 20, procedure = "one", statistics = TRUE, CI = TRUE)),
  CompareMeans_ind = quote(CompareMeans(data = "dr", varX = "NumFruit", grp = "Fertilizer", procedure = "independent", statistics = TRUE, CI = TRUE)),
  CompareMeans_pair= quote(CompareMeans(data = "ir", varX = "Sepal.Length", varY = "Sepal.Width", procedure = "paired", statistics = TRUE)),
  Normality        = quote(NormalityTest(data = "mt", var = c("mpg", "hp"), method = "swilk")),
  Normality_grp    = quote(NormalityTest(data = "ir", var = "Sepal.Length", grp = "Species", method = "swilk")),
  Crosstab         = quote(Crosstab(data = "mt", rowVar = "cyl", colVar = "am", chisq = TRUE, phi = TRUE, cramersv = TRUE, expFreq = TRUE)),
  Correlation      = quote(BivariateCorrelationTest(data = "mt", var = c("mpg", "hp", "wt"), method = "pearson", statistics = TRUE)),
  Correlation_sp   = quote(BivariateCorrelationTest(data = "mt", var = c("mpg", "hp", "wt"), method = "spearman")),
  Regression       = quote(LinearRegressionAnalysis(data = "mt", depVar = "mpg", indepVar = c("wt", "hp"), confInt = TRUE, covMatrix = TRUE, VIF = TRUE)),
  AOV_CRD          = quote(AOVTest(data = "pg", design = "CRD", respvar = "weight", factor1 = "group", pwTest = "Tukey", pwVar = "group", descriptive = TRUE, normality = TRUE, homogeneity = TRUE)),
  AOV_CRD_lsd      = quote(AOVTest(data = "pg", design = "CRD", respvar = "weight", factor1 = "group", pwTest = "LSD", pwVar = "group")),
  AOV_CRD_duncan   = quote(AOVTest(data = "pg", design = "CRD", respvar = "weight", factor1 = "group", pwTest = "Duncan", pwVar = "group")),
  AOV_CRD_2fac     = quote(AOVTest(data = "tg", design = "CRD", respvar = "len", factor1 = c("supp", "dose"))),
  AOV_RCBD         = quote(AOVTest(data = "dd", design = "RCBD", respvar = "Yield", factor1 = "Nitrogen", rep1 = "Rep", pwTest = "LSD", pwVar = "Nitrogen")),
  AOV_RCBD_tukey   = quote(AOVTest(data = "dd", design = "RCBD", respvar = "Yield", factor1 = c("Nitrogen", "Variety"), rep1 = "Rep", pwTest = "Tukey", pwVar = "Variety")),
  AOV_SplitRCBD    = quote(AOVTest(data = "dd", design = "SplitRCBD", respvar = "Yield", factor1 = "Nitrogen", factor2 = "Variety", rep1 = "Rep", pwTest = "Tukey", pwVar = "Nitrogen")),
  AOV_LSD          = quote(AOVTest(data = "mm", design = "LSD", respvar = "GRAINYIELD", factor1 = "MAIZE", rep1 = "ROW", rep2 = "COLUMN", pwTest = "LSD", pwVar = "MAIZE")),
  PCA              = quote(PCA(data = "ir", var = c("Sepal.Length", "Sepal.Width", "Petal.Length", "Petal.Width"), scatterMatx = FALSE, scree = FALSE, biplot = FALSE, pcaplot = FALSE, saveScore = FALSE)),
  Kmeans           = quote({ set.seed(1); ClusterKmeans(data = "ir", var = c("Sepal.Length", "Petal.Length"), kgraph = FALSE, saveMem = FALSE, clusterNum = 3) }),
  Agglo            = quote(ClusterAgglo(data = "ir", var = c("Sepal.Length", "Petal.Length"), method = "average", dendrogram = FALSE, clusterMem = FALSE)),
  Discriminant     = quote(Discriminant(data = "ir", var = c("Sepal.Length", "Petal.Length"), grp = "Species")),
  FitDistrib       = quote(FitDistrib(data = "mt", var = "mpg", distribution = "norm")),
  KruskalWallis    = quote(KruskalWallisTest(data = "pg", respvar = "weight", grp = "group")),
  Heterosk         = quote(HeteroskedasticityTest(data = "pg", var = "weight", grp = "group", method = "levene")),
  Skew_kurt        = quote(print(c(skewness(mt$mpg), kurtosis(mt$mpg), coefVar(mt$mpg), se.mean(mt$mpg))))
)

for (nm in names(cases)) {
  txt <- tryCatch(
    capture.output({ r <- eval(cases[[nm]]); if (!is.null(r)) print(r) }),
    error = function(e) paste("ERROR:", conditionMessage(e)))
  writeLines(txt, file.path(outdir, paste0(nm, ".txt")))
  cat(sprintf("%-18s %s\n", nm, if (length(txt) && grepl("^ERROR", txt[1])) txt[1] else paste(length(txt), "lines")))
}
