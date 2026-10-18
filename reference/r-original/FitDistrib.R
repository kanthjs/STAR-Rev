`FitDistrib` <-
function (data, outputPath, yVar, byVar = NULL, distrib = c("beta", 
    "exp", "gamma", "geom", "lnorm", "logis", "nbinom", "norm", 
    "pois"), fitMethod = c("mle", "mme", "mge")) 
UseMethod("FitDistrib")
