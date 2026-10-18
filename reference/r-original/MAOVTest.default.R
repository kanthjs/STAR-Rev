`MAOVTest.default` <-
function (data, outputPath, yVars, factorVar, repVar = NULL, 
    testStat = c("Pillai", "Wilks", "Hotelling-Lawley", "Roy"), 
    mymodel, descriptive = FALSE, correlate = FALSE, doNormalTest = FALSE, 
    doBoxM = FALSE, doSphericity = FALSE, saveSSH = FALSE, saveSSE = FALSE) 
{
    if (is.character(data)) {
        data <- eval(parse(text = data))
    }
    tempData <- data
    if (!is.null(repVar)) {
        tempData[, repVar] <- factor(tempData[, repVar])
    }
    if (!is.null(factorVar)) {
        tempData[, factorVar] <- factor(tempData[, factorVar])
    }
    ClassInformation(tempData[, c(factorVar, repVar, yVars)], 
        respvar = c(factorVar, repVar, yVars))
    cat("\n\n")
    data <- na.omit(data[, c(yVars, factorVar, repVar)])
    if (!is.null(factorVar)) {
        data[, factorVar] <- factor(data[, factorVar])
    }
    if (!is.null(repVar)) {
        data[, repVar] <- factor(data[, repVar])
        nBalObs <- nlevels(data[, repVar]) * nlevels(data[, factorVar])
        if (nrow(data) != nBalObs) {
            stop("Data set should be balanced.")
        }
    }
    results <- list()
    if (descriptive) {
        DescriptiveStatistics(data, var = yVars, grp = NULL, 
            statistics = c("n", "mean", "sd", "min", "max"))
        cat("\n")
    }
    if (correlate) {
        cat("\n")
        BivariateCorrelationTest(data, var = yVars, method = "pearson", 
            alternative = "two.sided", statistics = FALSE)
    }
    if (doNormalTest) {
        normTestOut <- mshapiro.test(t(data[, yVars]))
        cat("MULTIVARIATE NORMALITY TEST\n")
        cat("---------------------------\n")
        normTestTable <- as.table(cbind("Wilk-Shapiro", formatC(normTestOut$statistic[[1]], 
            digits = 4, format = "f"), formatC(normTestOut$p[[1]], 
            digits = 4, format = "f")))
        rownames(normTestTable) <- ""
        colnames(normTestTable) <- c("Statistic", "Value", "Prob")
        print(normTestTable, quote = FALSE)
    }
    if (doBoxM) {
        boxMTestOut <- BoxsMTest(data, yVars, factorVar, alpha = 0.05)
        cat("\n\nBOX'S M TEST FOR HOMOGENEITY OF COVARIANCE MATRICES\n")
        cat("---------------------------------------------------\n")
        if (!(is.nan(as.matrix(boxMTestOut$MBox)))) {
            if (length(boxMTestOut) == 4) {
                boxMTestTable <- as.table(cbind(formatC(boxMTestOut$MBox, 
                  digits = 4, format = "f"), formatC(boxMTestOut$ChiSq, 
                  digits = 4, format = "f"), formatC(boxMTestOut$df, 
                  digits = 0, format = "f"), formatC(boxMTestOut$pValue, 
                  digits = 4, format = "f")))
                rownames(boxMTestTable) <- ""
                colnames(boxMTestTable) <- c("Box's M", "Chi-Square", 
                  "df", "Prob")
            }
            else {
                boxMTestTable <- as.table(cbind(formatC(boxMTestOut$MBox, 
                  digits = 4, format = "f"), formatC(boxMTestOut$F, 
                  digits = 4, format = "f"), formatC(boxMTestOut$df1, 
                  digits = 0, format = "f"), formatC(boxMTestOut$df2, 
                  digits = 0, format = "f"), formatC(boxMTestOut$pValue, 
                  digits = 4, format = "f")))
                rownames(boxMTestTable) <- ""
                colnames(boxMTestTable) <- c("Box M", "F", "df1", 
                  "df2", "Prob")
            }
            print(boxMTestTable)
        }
        else cat("***\nBox's M statistic cannot be computed. \nDeterminant of pooled covariance matrix is less than 0.\n***")
    }
    if (doSphericity) {
        R <- cor(data[, yVars])
        n <- nrow(data)
        p <- length(yVars)
        chiSqVal <- -(n - 1 - (2 * p + 5)/6) * log(det(R))
        chiSqDf <- p * (p - 1)/2
        pValue = pchisq(chiSqVal, chiSqDf, lower.tail = F)
        cat("\n\nBARTLETT'S SPHERICITY TEST\n")
        cat("--------------------------\n")
        sphereTestTable <- as.table(cbind("Chi-Square", formatC(chiSqVal, 
            digits = 4, format = "f"), formatC(chiSqDf, digits = 0, 
            format = "f"), formatC(pValue, digits = 4, format = "f")))
        rownames(sphereTestTable) <- ""
        colnames(sphereTestTable) <- c("Statistic", "Value", 
            "Df", "Prob")
        print(sphereTestTable, quote = FALSE)
    }
    linmodel = (lm((mymodel), data = data))
    maov <- manova(linmodel, data = data)
    cat("\n\nSUM OF SQUARES AND CROSS PRODUCTS FOR THE HYPOTHESIS \n")
    cat("----------------------------------------------\n")
    cat(paste("\nTERM:", factorVar, "\n\n"))
    trtSS = summary(maov)$SS[[factorVar]]
    print(trtSS)
    if (saveSSH) 
        write.csv(trtSS, paste(outputPath, "/", factorVar, "SS.csv", 
            sep = ""))
    if (!is.null(repVar)) {
        cat(paste("\nTERM:", repVar, "\n\n"))
        repSS = summary(maov)$SS[[repVar]]
        print(repSS)
        if (saveSSH) 
            write.csv(repSS, paste(outputPath, "/", repVar, "SS.csv", 
                sep = ""))
    }
    cat("\n\nSUM OF SQUARES AND CROSS PRODUCTS FOR ERROR\n")
    cat("-------------------------------------\n")
    errSS = summary(maov)$SS$Residuals
    print(errSS)
    if (saveSSE) 
        write.csv(errSS, paste(outputPath, "/errorSS.csv", sep = ""))
    if (is.null(repVar)) {
        design = "CRD"
    }
    else design = "RCBD"
    cat(paste("\n\nMULTIVARIATE ANALYSIS OF VARIANCE IN ", design, 
        "\n", sep = ""))
    cat("-----------------------------------------\n")
    print(summary(maov, test = testStat))
}
