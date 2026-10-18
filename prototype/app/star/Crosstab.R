`Crosstab` <-
function (data, rowVar, colVar, freqVar = NULL, obsFreq = TRUE, 
    expFreq = FALSE, stdresid = FALSE, totPercent = FALSE, rowPercent = FALSE, 
    colPercent = FALSE, chisq = FALSE, phi = FALSE, cramersv = FALSE, 
    contingency = FALSE) 
UseMethod("Crosstab")
