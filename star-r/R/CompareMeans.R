`CompareMeans` <-
function (data, varX, varY = NULL, grp = NULL, testVal = 0, procedure = c("one", 
    "paired", "independent"), alternative = c("two.sided", "greater", 
    "less"), statistics = FALSE, CI = FALSE, confLevel = 0.95, 
    normality = NULL, alpha = 0.05) 
UseMethod("CompareMeans")
