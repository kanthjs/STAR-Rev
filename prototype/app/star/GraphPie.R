`GraphPie` <-
function (data, outputPath, cVar, mTitle = NULL, wVar = NULL, 
    byVar = NULL, sliceColors, dispLabels = FALSE, useLabels = c("counts", 
        "pcts", "cats", "pctcats", "countcats"), showLeg = FALSE, 
    legPos = c("bottomright", "bottom", "bottomleft", "left", 
        "topleft", "top", "topright", "right", "center"), legTitle = NULL, 
    legCol = 1, pieDensity, pieLineAngle, boxed = TRUE, multGraphs = FALSE, 
    numRowsGraphs = 1, numColsGraphs = 1, orientGraphs = c("left-right", 
        "top-bottom")) 
UseMethod("GraphPie")
