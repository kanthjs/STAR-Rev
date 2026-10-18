`GraphScatter.default` <-
function (data, outputPath, xVar, yVar, mTitle = NULL, xAxisLab = NULL, 
    yAxisLab = NULL, xMinValue = NULL, xMaxValue = NULL, yMinValue = NULL, 
    yMaxValue = NULL, axisLabelStyle = 1, byVar = NULL, boxed = TRUE, 
    pointCol, pointChar, pointCharSize, dispLine = FALSE, lineType = NULL, 
    lineCol = NULL, lineWidth, dispR2P = FALSE, r2PPos = c("bottomright", 
        "bottom", "bottomleft", "left", "topleft", "top", "topright", 
        "right", "center"), multGraphs = FALSE, numRowsGraphs = 1, 
    numColsGraphs = 1, orientGraphs = c("left-right", "top-bottom")) 
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
    if (!is.null(byVar)) {
        byNLev = nlevels(data[, byVar])
        numGraphs = byNLev * length(xVar) * length(yVar)
    }
    else {
        byNLev = 1
        numGraphs = length(xVar) * length(yVar)
    }
    graphNum = 1
    k = 1
    for (m in 1:byNLev) {
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
        yStat = NULL
        for (i in 1:length(xVar)) {
            for (j in 1:length(yVar)) {
                if (!multGraphs) {
                  png(filename = paste(outputPath, "scatter_", 
                    k, ".png", sep = ""))
                  par(mfrow = c(numRowsGraphs, numColsGraphs))
                }
                else {
                  if (graphNum == 1 || graphNum%%numCells == 
                    1 || numCells == 1) {
                    widthAdj = numColsGraphs * 480
                    heightAdj = numRowsGraphs * 480
                    png(filename = paste(outputPath, "scatter_", 
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
                tempData = na.omit(tempData1[, c(xVar[i], yVar[j])])
                if (nrow(tempData) > 0) {
                  yMinLim = NULL
                  yMaxLim = NULL
                  yVarLim = NULL
                  xMinLim = NULL
                  xMaxLim = NULL
                  xVarLim = NULL
                  if (is.na(yMinValue[j]) && is.na(yMaxValue[j])) {
                    yVarLim = NULL
                  }
                  else {
                    yMinLim[j] = if (!is.na(yMinValue[j])) {
                      yMinValue[j]
                    }
                    else min(0, min(tempData[, yVar[j]], na.rm = TRUE))
                    yMaxLim[j] = if (!is.na(yMaxValue[j])) {
                      yMaxValue[j]
                    }
                    else max(tempData[, yVar[j]], na.rm = TRUE)
                    yVarLim = c(yMinLim[j], yMaxLim[j])
                  }
                  if (is.na(xMinValue[i]) && is.na(xMaxValue[i])) {
                    xVarLim = NULL
                  }
                  else {
                    xMinLim[i] = if (!is.na(xMinValue[i])) {
                      xMinValue[i]
                    }
                    else min(0, min(tempData[, xVar[i]], na.rm = TRUE))
                    xMaxLim[i] = if (!is.na(xMaxValue[i])) {
                      xMaxValue[i]
                    }
                    else max(tempData[, xVar[i]], na.rm = TRUE)
                    xVarLim = c(xMinLim[i], xMaxLim[i])
                  }
                  plot(tempData[, xVar[i]], tempData[, yVar[j]], 
                    ylab = yAxisLab[j], xlab = xAxisLab[i], main = mTitle, 
                    type = "p", pch = pointChar, cex = pointCharSize, 
                    col = pointCol, xlim = xVarLim, ylim = yVarLim, 
                    las = axisLabelStyle)
                  if (!is.null(subTitle)) 
                    mtext(side = 3, text = subTitle, line = 0.25, 
                      cex = 0.9)
                  if (boxed) 
                    box(lty = 1)
                  if (nrow(tempData) > 1) {
                    regMod = lm(tempData[, yVar[j]] ~ tempData[, 
                      xVar[i]])
                    regModSum = summary(regMod)
                    if (dispLine) {
                      abline(regMod, cex = pointCharSize, col = lineCol, 
                        lty = lineType)
                      if (dispR2P) {
                        rSqr = regModSum$r.squared * 100
                        pValue = pf(regModSum$fstatistic[1], 
                          regModSum$fstatistic[2], regModSum$fstatistic[3], 
                          lower.tail = FALSE)
                        r2P = vector("expression", 2)
                        r2P[1] = substitute(expression(italic(R)^2 ~ 
                          "(%)" == rSqr), list(rSqr = format(rSqr, 
                          digits = 3)))[2]
                        r2P[2] = substitute(expression(italic(p) == 
                          pValue), list(pValue = format(pValue, 
                          digits = 4)))[2]
                        legend(r2PPos, r2P, bty = "n", cex = 0.75)
                      }
                    }
                  }
                }
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
