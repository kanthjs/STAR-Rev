`GraphBar` <-
function (data, outputPath, nVar, cVar, clustVars = NULL, mTitle = NULL, 
    yAxisLab = NULL, xAxisLab = NULL, minValue = NULL, maxValue = NULL, 
    typeData = c("raw", "sumstat"), descStat = c(NULL, "mean", 
        "median", "sum", "freq"), barsHoriz = FALSE, barsClust = TRUE, 
    byVar = NULL, errBars = FALSE, typeErrBar = c(NULL, "stdDev", 
        "stdErr", "confInt"), errMult = 1, confLev = NULL, axisLabelStyle = 1, 
    barColor = NULL, showLeg = FALSE, legPos = c("bottomright", 
        "bottom", "bottomleft", "left", "topleft", "top", "topright", 
        "right", "center"), legTitle = NULL, legCol = 1, boxed = TRUE, 
    multGraphs = FALSE, numRowsGraphs = 1, numColsGraphs = 1, 
    orientGraphs = c("left-right", "top-bottom"), showCatVarLevels = TRUE, 
    barDensity, barLineAngle) 
UseMethod("GraphBar")
