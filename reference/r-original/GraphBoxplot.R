`GraphBoxplot` <-
function (data, outputPath, nVar, cVars = NULL, mTitle = NULL, 
    nAxisLab = NULL, cAxisLab = NULL, yMinValue = NULL, yMaxValue = NULL, 
    axisLabelStyle = 1, plotHoriz = FALSE, byVar = NULL, boxSize = 0.8, 
    boxColor = NULL, boxFillColor = NULL, medLineType = 1, medLineWidth = 3, 
    medColor = NULL, whiskLineType = 2, whiskLineWidth = 1, whiskColor = NULL, 
    outChar = 1, outCharSize = 1, outColor = NULL, boxed = TRUE, 
    multGraphs = FALSE, numRowsGraphs = 1, numColsGraphs = 1, 
    orientGraphs = c("left-right", "top-bottom")) 
UseMethod("GraphBoxplot")
