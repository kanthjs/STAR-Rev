`GraphBoxplot.default` <-
function (data, outputPath, nVar, cVars = NULL, mTitle = NULL, 
    nAxisLab = NULL, cAxisLab = NULL, yMinValue = NULL, yMaxValue = NULL, 
    axisLabelStyle = 1, plotHoriz = FALSE, byVar = NULL, boxSize = 0.8, 
    boxColor = NULL, boxFillColor = NULL, medLineType = 1, medLineWidth = 3, 
    medColor = NULL, whiskLineType = 2, whiskLineWidth = 1, whiskColor = NULL, 
    outChar = 1, outCharSize = 1, outColor = NULL, boxed = TRUE, 
    multGraphs = FALSE, numRowsGraphs = 1, numColsGraphs = 1, 
    orientGraphs = c("left-right", "top-bottom")) 
{
    if (is.character(data)) {
        data <- eval(parse(text = data))
    }
    if (is.null(cVars)) {
        cVar <- make.unique(c(names(data), "grp.Var"))[length(make.unique(c(names(data), 
            "grp.Var")))]
        data[, cVar] = rep(1, nrow(data))
    }
    if (is.null(cVars)) {
        cVar[1] <- make.unique(c(names(data), "cVars"))[length(make.unique(c(names(data), 
            "grp.Var")))]
        data[, cVar[1]] = rep(1, nrow(data))
    }
    else {
        if (length(cVars) == 2) {
            cVar = paste(cVars[1], cVars[2], sep = "-")
            data[, cVar] = paste(data[, cVars[1]], data[, cVars[2]], 
                sep = "-")
        }
        else if (length(cVars) == 3) {
            cVar = paste(cVars[1], cVars[2], cVars[3], sep = "-")
            data[, cVar] = paste(data[, cVars[1]], data[, cVars[2]], 
                data[, cVars[3]], sep = "-")
        }
        else {
            cVar = cVars[1]
            data[, cVar] = data[, cVars[1]]
        }
    }
    data[, cVar] <- factor(data[, cVar])
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
        tempData1 = tempData1[, c(nVar, cVar)]
        if (all(is.na(tempData1[, cVar]))) {
            tempData1[, cVar] = rep(1, nrow(tempData1))
        }
        tempData1 = tempData1[order(tempData1[, cVar]), ]
        namesX = levels(tempData1[, cVar])
        for (i in 1:length(nVar)) {
            if (!multGraphs) {
                png(filename = paste(outputPath, "boxplot", k, 
                  ".png", sep = ""))
                par(mfrow = c(numRowsGraphs, numColsGraphs))
            }
            else {
                if (graphNum == 1 || graphNum%%numCells == 1 || 
                  numCells == 1) {
                  widthAdj = numColsGraphs * 480
                  heightAdj = numRowsGraphs * 480
                  png(filename = paste(outputPath, "boxplot", 
                    k, ".png", sep = ""), width = widthAdj, height = heightAdj)
                  if (orientGraphs == "top-bottom") {
                    par(mfcol = c(numRowsGraphs, numColsGraphs))
                  }
                  else if (orientGraphs == "left-right") {
                    par(mfrow = c(numRowsGraphs, numColsGraphs))
                  }
                }
            }
            tempData = na.omit(tempData1[, c(nVar[i], cVar)])
            if (nrow(tempData) > 0) {
                if (is.na(yMinValue[i]) && is.na(yMaxValue[i])) {
                  yVarLim = NULL
                }
                else {
                  yMinLim = if (!is.na(yMinValue[i])) {
                    yMinValue[i]
                  }
                  else min(tempData[, nVar[i]], na.rm = TRUE)
                  yMaxLim = if (!is.na(yMaxValue[i])) {
                    yMaxValue[i]
                  }
                  else max(tempData[, nVar[i]], na.rm = TRUE)
                  yVarLim = c(yMinLim, yMaxLim)
                }
                if (plotHoriz) {
                  yAxisLab = cAxisLab
                  xAxisLab = nAxisLab[i]
                }
                else {
                  xAxisLab = cAxisLab
                  yAxisLab = nAxisLab[i]
                }
                boxplot(tempData[, nVar[i]] ~ tempData[, cVar], 
                  ylab = yAxisLab, xlab = xAxisLab, main = mTitle, 
                  ylim = yVarLim, las = axisLabelStyle, frame.plot = boxed, 
                  horizontal = plotHoriz, names.arg = namesX, 
                  pars = list(boxwex = boxSize, boxcol = boxColor, 
                    boxfill = boxFillColor, medlty = medLineType, 
                    medlwd = medLineWidth, medcol = medColor, 
                    whisklty = whiskLineType, whisklwd = whiskLineWidth, 
                    whiskcol = whiskColor, staplecol = whiskColor, 
                    outpch = outChar, outcex = outCharSize, outcol = outColor))
                if (!is.null(subTitle)) 
                  mtext(side = 3, text = subTitle, line = 0.25, 
                    cex = 0.9)
            }
            if ((graphNum%%numCells == 0) || graphNum == numGraphs) {
                dev.off()
                k = k + 1
            }
            graphNum = graphNum + 1
        }
    }
}
