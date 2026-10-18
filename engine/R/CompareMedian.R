`CompareMedian` <-
function (data, varX, varY = NULL, grp = NULL, procedure = c("one", 
    "paired", "independent"), altHypo = c("two.sided", "less", 
    "greater"), hypoValue = 0, confInt = FALSE, confLevel = 0.95) 
UseMethod("CompareMedian")
