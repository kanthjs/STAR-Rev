`MDS.default` <-
function (data, outputPath, inputType = c("raw", "distMat"), 
    vars = NULL, idVar, type = c("Classical", "Nonmetric"), distClass = c("Euclidean", 
        "Maximum", "Manhattan", "Canberra", "Minkowski"), distNonmet = c("Manhattan", 
        "Euclidean", "Canberra", "Bray", "Kulczynski", "Jaccard", 
        "Gower", "AltGower", "Morisita", "Horn", "Mountford", 
        "Raup", "Binomial", "Chao", "Cao"), dimnum = 2, useIdVar = FALSE, 
    pChars, pSizes, pCol, showLeg = FALSE, legTitle, legPos = "bottomright", 
    legNcol = 1, descriptive = FALSE, correlate = FALSE) 
{
    if (is.character(data)) {
        nameData <- data
        if (!exists(nameData)) {
            stop(paste("The object '", nameData, "' does not exist.", 
                sep = ""))
        }
        tempData <- eval(parse(text = data))
    }
    else {
        if (is.data.frame(data)) {
            nameData <- paste(deparse(substitute(data)))
            tempData <- data
        }
        else {
            stop("The argument should either be a data frame or a character string indicating the name of the data frame.")
        }
    }
    if (inputType == "raw") {
        data[, idVar] <- factor(data[, idVar])
        numObsNM = nrow((na.omit(data[, vars])))
        numObs = nrow((data[, vars]))
        if (numObs == numObsNM) {
            cat("Number of Observations:", numObs, "\n")
        }
        else {
            cat("Number of Observations:", numObs, "\n")
            cat("Number of Observations Used:", numObsNM, "\n\n")
        }
        tempData <- na.omit(tempData[, c(vars, idVar)])
    }
    else {
        tempData <- na.omit(tempData)
        d2 <- as.matrix(tempData[, -1])
        rownames(d2) <- NULL
        colnames(d2) <- NULL
        if (!isSymmetric(d2)) {
            stop("Input matrix should be symmetric.")
        }
    }
    if (!is.data.frame(tempData)) {
        stop("The object should be of type data frame.")
    }
    type <- match.arg(type)
    options(width = 5000, digits = 6)
    if (descriptive) {
        DescriptiveStatistics(data = tempData, var = vars, statistics = c("nnmiss", 
            "mean", "sd", "se.mean"))
        cat("\n\n")
    }
    if (correlate) {
        BivariateCorrelationTest(data = tempData, var = vars, 
            method = "pearson", alternative = "two.sided", statistics = FALSE)
        cat("\n\n")
    }
    if (type == "Classical") {
        cat("CLASSICAL MULTIDIMENSIONAL SCALING \n\n")
        if (inputType == "raw") {
            distClass <- match.arg(distClass)
            d <- dist(tempData[, vars], method = tolower(distClass))
        }
        else d <- tempData[, -1]
        capture.output(fit <- cmdscale(d, eig = TRUE, k = as.numeric(dimnum)))
        points <- data.frame(fit$points)
        colnames(points) <- make.unique(rep("MDS", dimnum + 1), 
            sep = "")[2:(dimnum + 1)]
        cat("\nPOINTS\n\n")
        print(points)
        cat("\n")
        eigenOut <- data.frame(fit$eig)
        if (nrow(eigenOut) > 10) {
            xLength = 10
        }
        else xLength = nrow(eigenOut)
        png(filename = paste(outputPath, "MDS_Screeplot.png", 
            sep = ""))
        plot(as.numeric(rownames(eigenOut))[1:xLength], eigenOut[1:xLength, 
            1], xaxt = "n", xlab = "Eigenvalue number", ylab = "Eigenvalue", 
            main = paste(type, "Scree Plot"), type = "b", pch = 16)
        axis(1, at = as.numeric(rownames(eigenOut))[1:xLength])
        dev.off()
        cat("\nP_", dimnum, " criterion: ", round(fit$GOF[2], 
            digits = 4), sep = "")
    }
    else if (type == "Nonmetric") {
        cat("NON-METRIC MULTIDIMENSIONAL SCALING \n\n")
        if (inputType == "raw") {
            distNonmet <- match.arg(distNonmet)
            if (tolower(distNonmet) == "bray") {
                d <- vegdist(tempData[, vars], method = "bray")
            }
            else d <- dist(tempData[, vars], method = tolower(distNonmet))
            capture.output(fit <- monoMDS(d, k = as.numeric(dimnum)))
        }
        else {
            d <- as.dist(tempData[, -1])
            capture.output(fit <- monoMDS(d = d, k = as.numeric(dimnum)))
        }
        cat("\nPOINTS \n\n")
        print(fit$points[1:dim(fit$points)[1], 1:dimnum])
        stressStat <- as.matrix(round(fit$stress, digits = 4))
        rownames(stressStat) <- ""
        cat("\nStress Statistic: ", stressStat[1, 1], sep = "")
        cat("\n")
        png(filename = paste(outputPath, "Stressplot.png", sep = ""))
        stressplot(fit)
        dev.off()
    }
    if (inputType == "raw") {
        grpLevels = levels(factor(data[, idVar]))
        idData = data[, idVar]
    }
    else {
        grpLevels = levels(factor(data[, 1]))
        idData = data[, 1]
    }
    if (!useIdVar) {
        grpCode <- data.frame(grpLevels, pChars, pCol)
        pCharNames <- make.unique(c(names(data), "pCharCode"), 
            sep = "")
        pCharName <- pCharNames[length(pCharNames)]
        pColName <- make.unique(c(pCharNames, "pColCode"), sep = "")
        pColName <- pColName[length(pColName)]
        tempData2 <- cbind(data, grpCode$pChars[match(idData, 
            grpCode[, "grpLevels"])], grpCode$pCol[match(idData, 
            grpCode[, "grpLevels"])])
        colnames(tempData2)[ncol(tempData2) - 1] <- pCharName
        colnames(tempData2)[ncol(tempData2)] <- pColName
    }
    else {
        pCharName = NULL
        pColName = NULL
        tempData2 = data
    }
    mdsGraph(tempData2, idVar, outputPath, pCharName, pColName, 
        grpLevels, idData, dimnum, fit, type, useIdVar, pChars, 
        pSizes, pCol, showLeg, legTitle, legPos, legNcol)
    return(list(Results = fit, Method = type))
}
