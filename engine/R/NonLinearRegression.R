`NonLinearRegression` <-
function (data, depVar, indepVar, statistics = FALSE, model, 
    modelExp = NULL, startValues = NULL, normality = FALSE, covMatrix = FALSE, 
    confInterval = FALSE, confLevel = 0.95) 
UseMethod("NonLinearRegression")
