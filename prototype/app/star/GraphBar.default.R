`GraphBar.default` <-
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
{
    if (is.character(data)) {
        data <- eval(parse(text = data))
    }
    clustVar = NULL
    if (is.null(clustVars)) {
        clustVar <- make.unique(c(names(data), "clustVar"))[length(make.unique(c(names(data), 
            "clustVar")))]
        data[, clustVar] = rep(1, nrow(data))
    }
    else {
        if (length(clustVars) == 2) {
            clustVar = paste(clustVars[1], clustVars[2], sep = "-")
            data[, clustVar] = paste(data[, clustVars[1]], data[, 
                clustVars[2]], sep = "-")
        }
        else if (length(clustVars) == 3) {
            clustVar = paste(clustVars[1], clustVars[2], clustVars[3], 
                sep = "-")
            data[, clustVar] = paste(data[, clustVars[1]], data[, 
                clustVars[2]], data[, clustVars[3]], sep = "-")
        }
        else {
            clustVar = clustVars[1]
            data[, clustVar] = data[, clustVars[1]]
        }
    }
    if (!is.null(cVar)) {
        data[, cVar] <- factor(data[, cVar])
    }
    if (!is.null(clustVar)) {
        data[, clustVar] <- factor(data[, clustVar])
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
        for (i in 1:length(nVar)) {
            if (!multGraphs) {
                png(filename = paste(outputPath, "bargraph_", 
                  k, ".png", sep = ""))
                par(mfrow = c(numRowsGraphs, numColsGraphs))
            }
            else {
                if (graphNum == 1 || graphNum%%numCells == 1 || 
                  numCells == 1) {
                  widthAdj = numColsGraphs * 480
                  heightAdj = numRowsGraphs * 480
                  png(filename = paste(outputPath, "bargraph_", 
                    k, ".png", sep = ""), width = widthAdj, height = heightAdj)
                  if (orientGraphs == "top-bottom") {
                    par(mfcol = c(numRowsGraphs, numColsGraphs))
                  }
                  else if (orientGraphs == "left-right") {
                    par(mfrow = c(numRowsGraphs, numColsGraphs))
                  }
                }
            }
            tempData2 = tempData1[, c(nVar[i], cVar, clustVar)]
            if (!all(is.na(tempData2[, nVar[i]])) && !all(is.na(tempData2[, 
                cVar]))) {
                allYStats = NULL
                yStatEB = NULL
                yStatEBj = NULL
                yStatLL = NULL
                yStatUL = NULL
                yStatHW = NULL
                if (typeData == "raw") {
                  if (!barsClust || (descStat != "mean")) 
                    errBars = FALSE
                  for (j in 1:nlevels(tempData2[, clustVar])) {
                    if (!is.null(clustVar)) {
                      tempData = tempData2[which(tempData2[, 
                        clustVar] == levels(tempData2[, clustVar])[j]), 
                        ]
                    }
                    else tempData = tempData2
                    yStat = NULL
                    if (descStat == "mean") {
                      yStat = tapply(tempData[, nVar[i]], tempData[, 
                        cVar], mean, na.rm = TRUE)
                      if (errBars) {
                        ySum <- tapply((tempData[, nVar[i]]), 
                          tempData[, cVar], sum, na.rm = TRUE)
                        yFreq <- ySum/yStat
                        yStdDev <- tapply(tempData[, nVar[i]], 
                          tempData[, cVar], sd, na.rm = TRUE)
                        yStdErr <- yStdDev/sqrt(yFreq)
                        if (typeErrBar == "stdDev") {
                          halfWidth = errMult * yStdDev
                        }
                        else if (typeErrBar == "stdErr") {
                          halfWidth = errMult * yStdErr
                        }
                        else if (typeErrBar == "confInt") {
                          halfWidth = qnorm((1 + confLev)/2) * 
                            yStdErr
                        }
                        lLimit = yStat - halfWidth
                        uLimit = yStat + halfWidth
                        yStatLL = rbind(yStatLL, lLimit)
                        yStatUL = rbind(yStatUL, uLimit)
                        yStatHW = rbind(yStatHW, halfWidth)
                      }
                    }
                    else if (descStat == "median") {
                      yStat <- tapply(tempData[, nVar[i]], tempData[, 
                        cVar], median, na.rm = TRUE)
                    }
                    else if (descStat == "sum") {
                      yStat <- tapply(tempData[, nVar[i]], tempData[, 
                        cVar], sum, na.rm = TRUE)
                    }
                    else if (descStat == "freq") {
                      yMean = tapply(tempData[, nVar[i]], tempData[, 
                        cVar], mean, na.rm = TRUE)
                      ySum <- tapply((tempData[, nVar[i]]), tempData[, 
                        cVar], sum, na.rm = TRUE)
                      yStat <- ySum/yMean
                    }
                    allYStats = rbind(allYStats, yStat)
                  }
                  allYStats = t(allYStats)
                  colnames(allYStats) = NULL
                }
                else if (typeData == "sumStat") {
                  tempData = na.omit(tempData2[order(tempData2[, 
                    cVar]), ])
                  allYStats = as.matrix(tempData[, nVar[i]])
                  errBars = FALSE
                }
                if (nlevels(tempData[, clustVar]) > 1) {
                  namesX = levels(tempData[, clustVar])
                }
                else namesX = NULL
                if (is.na(minValue[i]) && is.na(maxValue[i]) && 
                  (!errBars)) {
                  yMinLim = NULL
                  yMaxLim = NULL
                }
                else {
                  yMinLim = if (!is.na(minValue[i])) {
                    minValue[i]
                  }
                  else {
                    if (!is.null(yStatLL)) 
                      min(0, allYStats, yStatLL, na.rm = TRUE)
                    else min(0, min(allYStats, na.rm = TRUE))
                  }
                  yMaxLim = if (!is.na(maxValue[i])) {
                    maxValue[i]
                  }
                  else {
                    if (barsClust) {
                      if (!is.null(yStatUL)) 
                        max(allYStats, yStatUL, na.rm = TRUE) * 
                          1.1
                      else max(allYStats, na.rm = TRUE) * 1.1
                    }
                    else {
                      maxVals = NULL
                      for (s in 1:ncol(allYStats)) maxVals[s] = sum(allYStats[, 
                        s], na.rm = TRUE)
                      max(maxVals, na.rm = TRUE) * 1.1
                    }
                  }
                }
                legLab = NULL
                if (showLeg) 
                  legLab = levels(tempData[, cVar])
                yAxisLabel = NULL
                xAxisLabel = NULL
                if (barsHoriz) {
                  xVarLim = c(yMinLim, yMaxLim)
                  yVarLim = NULL
                  if (!is.null(xAxisLab)) 
                    yAxisLabel = xAxisLab
                  if (!is.null(yAxisLab[i])) 
                    xAxisLabel = yAxisLab[i]
                }
                else {
                  yVarLim = c(yMinLim, yMaxLim)
                  xVarLim = NULL
                  if (!is.null(yAxisLab[i])) 
                    yAxisLabel = yAxisLab[i]
                  else yAxisLabel = NULL
                  if (!is.null(xAxisLab)) 
                    xAxisLabel = xAxisLab
                  else xAxisLabel = NULL
                }
                barplot1 <- barplot(allYStats, ylab = yAxisLabel, 
                  space = c(0.2, 1), main = mTitle, names.arg = namesX, 
                  horiz = barsHoriz, beside = barsClust, col = barColor, 
                  xlim = xVarLim, ylim = yVarLim, density = barDensity, 
                  angle = barLineAngle, legend.text = legLab, 
                  args.legend = list(x = legPos, title = legTitle, 
                    ncol = legCol), las = 1)
                if (boxed) 
                  box(lty = 1)
                if (nlevels(tempData[, clustVar]) > 1 && barsClust && 
                  showCatVarLevels) {
                  if (barsHoriz) {
                    mtext(side = 2, at = barplot1, text = rep(levels(tempData[, 
                      cVar]), nlevels(tempData[, clustVar])), 
                      line = 0, cex = 0.75, las = axisLabelStyle)
                  }
                  else {
                    mtext(side = 1, at = barplot1, text = rep(levels(tempData[, 
                      cVar]), nlevels(tempData[, clustVar])), 
                      line = 0, cex = 0.75, las = axisLabelStyle)
                  }
                }
                if (!is.null(subTitle)) 
                  mtext(side = 3, text = subTitle, line = 0.25, 
                    cex = 0.9)
                mtext(side = 1, text = xAxisLabel, line = 2, 
                  cex = 0.85)
                if (errBars) {
                  lLimits = matrix(t(yStatLL), c(nlevels(tempData[, 
                    cVar]), nlevels(tempData[, clustVar])))
                  uLimits = matrix(t(yStatUL), c(nlevels(tempData[, 
                    cVar]), nlevels(tempData[, clustVar])))
                  if (barsHoriz) {
                    arrows(lLimits, barplot1, uLimits, barplot1, 
                      angle = 90, code = 3, length = 0.1)
                  }
                  else arrows(barplot1, lLimits, barplot1, uLimits, 
                    angle = 90, code = 3, length = 0.1)
                }
            }
            if ((graphNum%%numCells == 0) || graphNum == numGraphs) {
                dev.off()
                k = k + 1
            }
            graphNum = graphNum + 1
        }
    }
}
