`GraphHist.default` <-
function (data, outputPath, nVar, mTitle = NULL, yAxisLabs, xAxisLabs, 
    yMinValues, yMaxValues, xMinValues, xMaxValues, useFreq = TRUE, 
    numBins = "Sturges", axisLabelStyle = 1, byVar = NULL, barColor, 
    dispCurve = FALSE, lineType, lineColor, lineWidth, boxed = TRUE, 
    multGraphs = FALSE, numRowsGraphs = 1, numColsGraphs = 1, 
    orientGraphs = c("left-right", "top-bottom")) 
{
    if (is.character(data)) {
        data <- eval(parse(text = data))
    }
    if (!is.null(byVar)) {
        data[, byVar] <- factor(data[, byVar])
    }
    if (!multGraphs) {
        numRowsGraphs = 1
        numColsGraphs = 1
    }
    numCells = numRowsGraphs * numColsGraphs
    numGroups = 1
    if (!is.null(byVar)) {
        numGroups = nlevels(data[, byVar])
        numGraphs = nlevels(data[, byVar]) * length(nVar)
    }
    else numGraphs = length(nVar)
    graphNum = 1
    k = 1
    for (m in 1:numGroups) {
        if (!is.null(byVar)) {
            tempData1 = data[which(data[, byVar] == levels(data[, 
                byVar])[m]), ]
            subTitle = paste(byVar, "=", levels(tempData1[, byVar])[m], 
                sep = " ")
        }
        else {
            tempData1 = data
            subTitle = NULL
        }
        for (j in 1:length(nVar)) {
            if (all(is.na(tempData1[, nVar[j]]))) {
                nVar[j] <- NA
            }
        }
        nVar = na.omit(nVar)
        if (length(nVar == 1)) {
            cVar <- make.unique(c(names(tempData1), "grp.Var"))[length(make.unique(c(names(tempData1), 
                "grp.Var")))]
            tempData1[, cVar] = c(1:nrow(tempData1))
        }
        tempData = tempData1[, c(nVar, cVar)]
        if (!all(is.na(tempData[, nVar]))) {
            histData = NULL
            minCount = NULL
            maxCount = NULL
            minDensity = NULL
            maxDensity = NULL
            for (i in 1:length(nVar)) {
                if (!multGraphs) {
                  png(filename = paste(outputPath, "histogram", 
                    k, ".png", sep = ""))
                  par(mfrow = c(numRowsGraphs, numColsGraphs))
                }
                else {
                  if (graphNum == 1 || graphNum%%numCells == 
                    1 || numCells == 1) {
                    widthAdj = numColsGraphs * 480
                    heightAdj = numRowsGraphs * 480
                    png(filename = paste(outputPath, "histogram", 
                      k, ".png", sep = ""), width = widthAdj, 
                      height = heightAdj)
                    if (orientGraphs == "top-bottom") {
                      par(mfcol = c(numRowsGraphs, numColsGraphs))
                    }
                    else if (orientGraphs == "left-right") {
                      par(mfrow = c(numRowsGraphs, numColsGraphs))
                    }
                  }
                }
                histData = hist(tempData[, nVar[i]], breaks = numBins, 
                  plot = F)
                minCount = min(histData$counts, na.rm = TRUE)
                maxCount = max(histData$counts, na.rm = TRUE)
                minDensity = min(histData$density, na.rm = TRUE)
                maxDensity = max(histData$density, na.rm = TRUE)
                xMinLim = if (!is.na(xMinValues[i])) {
                  xMinValues[i]
                }
                else min(0, min(tempData[, nVar[i]], na.rm = TRUE))
                xMaxLim = if (!is.na(xMaxValues[i])) {
                  xMaxValues[i]
                }
                else max(tempData[, nVar[i]], na.rm = TRUE) * 
                  1.25
                xVarLim = c(xMinLim, xMaxLim)
                if (is.na(yMinValues[i]) && is.na(yMaxValues[i])) {
                  yVarLim = NULL
                }
                else {
                  if (useFreq) {
                    yMinLim = if (!is.na(yMinValues[i])) {
                      yMinValues[i]
                    }
                    else minCount
                    yMaxLim = if (!is.na(yMaxValues[i])) {
                      yMaxValues[i]
                    }
                    else {
                      maxCount
                    }
                  }
                  else {
                    yMinLim = if (!is.na(yMinValues[i])) {
                      yMinValues[i]
                    }
                    else minDensity
                    yMaxLim = if (!is.na(yMaxValues[i])) {
                      yMaxValues[i]
                    }
                    else {
                      if (dispCurve) 
                        maxDensity * 1.25
                      else maxDensity
                    }
                  }
                  yVarLim = c(yMinLim, yMaxLim)
                }
                plot(histData, xlim = xVarLim, ylim = yVarLim, 
                  col = barColor, freq = useFreq, main = mTitle, 
                  ylab = yAxisLabs[i], xlab = xAxisLabs[i], las = axisLabelStyle)
                if (!is.null(subTitle)) 
                  mtext(side = 3, text = subTitle, line = 0.25, 
                    cex = 0.9)
                if (dispCurve && !useFreq) 
                  lines(density(tempData[, nVar[i]], na.rm = TRUE), 
                    col = lineColor, lty = lineType, lwd = lineWidth)
                if (boxed) 
                  box(lty = 1)
                if ((graphNum%%numCells == 0) || graphNum == 
                  numGraphs) {
                  dev.off()
                  k = k + 1
                }
                graphNum = graphNum + 1
            }
        }
    }
}
