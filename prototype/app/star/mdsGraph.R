`mdsGraph` <-
function (data, idVar, outputPath, pCharName = NULL, pColName, 
    grpLevels = NULL, idData, dimnum, fit, type, useIdVar = FALSE, 
    pChars, pSizes, pCol, showLeg, legTitle, legPos, legNcol) 
UseMethod("mdsGraph")
