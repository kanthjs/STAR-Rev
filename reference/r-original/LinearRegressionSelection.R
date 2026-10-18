`LinearRegressionSelection` <-
function (data, depVar, indepVar, constant = TRUE, correlate = FALSE, 
    selection = "none", selectionStat = NULL, confInt = FALSE, 
    confLevel = 0.95, covMatrix = FALSE, normality = NULL, heteroskedasticity = NULL, 
    autoCorr = FALSE, VIF = FALSE, COOKS = FALSE, leverage = FALSE) 
UseMethod("LinearRegressionSelection")
