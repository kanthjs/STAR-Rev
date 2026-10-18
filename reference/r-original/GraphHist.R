`GraphHist` <-
function (data, outputPath, nVar, mTitle = NULL, yAxisLabs, xAxisLabs, 
    yMinValues, yMaxValues, xMinValues, xMaxValues, useFreq = TRUE, 
    numBins = "Sturges", axisLabelStyle = 1, byVar = NULL, barColor, 
    dispCurve = FALSE, lineType, lineColor, lineWidth, boxed = TRUE, 
    multGraphs = FALSE, numRowsGraphs = 1, numColsGraphs = 1, 
    orientGraphs = c("left-right", "top-bottom")) 
UseMethod("GraphHist")
