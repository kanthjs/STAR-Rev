`mdsGraph.default` <-
function (data, idVar, outputPath, pCharName = NULL, pColName, 
    grpLevels = NULL, idData, dimnum, fit, type, useIdVar = FALSE, 
    pChars, pSizes, pCol, showLeg, legTitle, legPos, legNcol) 
{
    x <- fit$points
    if (useIdVar) {
        plotType = "n"
        pChars = grpLevels
        pCharData = rownames(x)
    }
    else {
        plotType = "p"
        pCharData = data[, pCharName]
    }
    if (dimnum == 2) {
        png(filename = paste(outputPath, "MDS_Coords1and2.png", 
            sep = ""))
        plot(x[, 1], x[, 2], xlab = "Coordinate 1", ylab = "Coordinate 2", 
            main = paste(type, "MDS"), type = plotType, pch = pCharData, 
            cex = pSizes, col = as.vector(data[, pColName]))
        if (useIdVar) 
            text(x[, 1], x[, 2], labels = idData, cex = pSizes, 
                col = as.vector(data[, pColName]))
        if (showLeg) 
            legend(legPos, title = legTitle, legend = grpLevels, 
                pch = pChars, cex = pSizes, col = pCol, ncol = legNcol)
        dev.off()
    }
    else if (dimnum > 2) {
        for (i in 1:(dimnum - 1)) {
            jlim = i + 1
            for (j in jlim:dimnum) {
                png(filename = paste(outputPath, "MDS_Coords", 
                  i, "and", j, ".png", sep = ""))
                plot(x[, i], x[, j], xlab = paste("Coordinate", 
                  i), ylab = paste("Coordinate", j), main = paste(type, 
                  "MDS"), type = plotType, pch = pCharData, cex = pSizes, 
                  col = as.vector(data[, pColName]))
                if (useIdVar) 
                  text(x[, i], x[, j], labels = idData, cex = pSizes, 
                    col = as.vector(data[, pColName]))
                if (showLeg) 
                  legend(legPos, title = legTitle, legend = grpLevels, 
                    pch = pChars, cex = pSizes, col = pCol, ncol = legNcol)
                dev.off()
            }
        }
    }
}
