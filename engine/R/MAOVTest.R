`MAOVTest` <-
function (data, outputPath, yVars, factorVar, repVar = NULL, 
    testStat = c("Pillai", "Wilks", "Hotelling-Lawley", "Roy"), 
    mymodel, descriptive = FALSE, correlate = FALSE, doNormalTest = FALSE, 
    doBoxM = FALSE, doSphericity = FALSE, saveSSH = FALSE, saveSSE = FALSE) 
UseMethod("MAOVTest")
