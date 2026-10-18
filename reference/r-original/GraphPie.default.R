`GraphPie.default` <-
function (data, outputPath, cVar, mTitle = NULL, wVar = NULL, 
    byVar = NULL, sliceColors, dispLabels = FALSE, useLabels = c("counts", 
        "pcts", "cats", "pctcats", "countcats"), showLeg = FALSE, 
    legPos = c("bottomright", "bottom", "bottomleft", "left", 
        "topleft", "top", "topright", "right", "center"), legTitle = NULL, 
    legCol = 1, pieDensity, pieLineAngle, boxed = TRUE, multGraphs = FALSE, 
    numRowsGraphs = 1, numColsGraphs = 1, orientGraphs = c("left-right", 
        "top-bottom")) 
{
    if (is.character(data)) {
        data <- eval(parse(text = data))
    }
    if (!is.null(cVar)) {
        data[, cVar] <- factor(data[, cVar])
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
        numGraphs = (nlevels(data[, byVar]))
    }
    else numGraphs = 1
    graphNum = 1
    k = 1
    for (m in 1:numGraphs) {
        if (!multGraphs) {
            png(filename = paste(outputPath, "piechart", k, ".png", 
                sep = ""))
            par(mfrow = c(numRowsGraphs, numColsGraphs))
        }
        else {
            if (graphNum == 1 || graphNum%%numCells == 1 || numCells == 
                1) {
                widthAdj = numColsGraphs * 480
                heightAdj = numRowsGraphs * 480
                png(filename = paste(outputPath, "piechart", 
                  k, ".png", sep = ""), width = widthAdj, height = heightAdj)
                if (orientGraphs == "top-bottom") {
                  par(mfcol = c(numRowsGraphs, numColsGraphs))
                }
                else if (orientGraphs == "left-right") {
                  par(mfrow = c(numRowsGraphs, numColsGraphs))
                }
            }
        }
        if (!is.null(byVar)) {
            tempData = data[which(data[, byVar] == levels(data[, 
                byVar])[m]), ]
            subTitle = paste(byVar, "=", levels(tempData[, byVar])[m], 
                sep = " ")
        }
        else {
            tempData = data
            subTitle = NULL
        }
        if (length(na.omit(tempData[, cVar])) > 0) {
            if (is.null(wVar)) {
                countsData = table(tempData[, cVar])
            }
            else {
                counts1 = tempData[order(tempData[, cVar]), ]
                countsData = as.table(counts1[, wVar])
                names(countsData) = levels(data[, cVar])
            }
            props1 = prop.table(countsData)
            propData = as.data.frame(props1)
            propData$Freq = propData$Freq * 100
            colnames(propData) = c("Var1", "Pcts")
            pieData = merge(countsData, propData, by = "Var1")
            pieData$Pcts = format(pieData$Pcts, digits = 4)
            namesX = levels(tempData[, cVar])
            legLab = NULL
            if (showLeg) 
                legLab = namesX
            labelText = NULL
            if (dispLabels) {
                if (useLabels == "counts") 
                  labelText = pieData$Freq
                else if (useLabels == "pcts") 
                  labelText = paste(pieData$Pcts, "%", sep = "")
                else if (useLabels == "cats") 
                  labelText = pieData$Var1
                else if (useLabels == "pctcats") 
                  labelText = paste(pieData$Var1, " ", pieData$Pcts, 
                    "%", sep = "")
                else if (useLabels == "countcats") 
                  labelText = paste(pieData$Var1, pieData$Freq)
            }
            else labelText = NA
            pie(pieData$Freq, main = mTitle, labels = labelText, 
                col = sliceColors, density = pieDensity, angle = pieLineAngle, 
                cex = 0.8, border = "black")
            if (!is.null(subTitle)) 
                mtext(side = 3, text = subTitle, line = 0.25, 
                  cex = 0.9)
            if (boxed) 
                box(lty = 1)
            if (showLeg) 
                legend(legPos, legend = legLab, title = legTitle, 
                  ncol = legCol, fill = sliceColors, density = pieDensity * 
                    2, angle = pieLineAngle, cex = 0.8)
            if ((graphNum%%numCells == 0) || graphNum == numGraphs) {
                dev.off()
                k = k + 1
            }
            graphNum = graphNum + 1
        }
    }
}
