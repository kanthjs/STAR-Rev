`GraphScatter` <-
function (data, outputPath, xVar, yVar, mTitle = NULL, xAxisLab = NULL, 
    yAxisLab = NULL, xMinValue = NULL, xMaxValue = NULL, yMinValue = NULL, 
    yMaxValue = NULL, axisLabelStyle = 1, byVar = NULL, boxed = TRUE, 
    pointCol, pointChar, pointCharSize, dispLine = FALSE, lineType = NULL, 
    lineCol = NULL, lineWidth, dispR2P = FALSE, r2PPos = c("bottomright", 
        "bottom", "bottomleft", "left", "topleft", "top", "topright", 
        "right", "center"), multGraphs = FALSE, numRowsGraphs = 1, 
    numColsGraphs = 1, orientGraphs = c("left-right", "top-bottom")) 
UseMethod("GraphScatter")
