`ssaTest` <-
function (exptl.design = c("RCB", "BIBD", "AugRCB", "AugLS", 
    "Alpha", "RowCol"), data, respvar, geno, row, column = NULL, 
    rep = NULL, env = NULL, is.random = FALSE, exclude = NULL, 
    descriptive = FALSE, varComp = FALSE, pairwise = c("none", 
        "Dunnett", "Tukey"), alpha = 0.05, diagPlot = FALSE, 
    outputPath = NULL) 
{
    exptl.design <- match.arg(exptl.design)
    pairwise <- match.arg(pairwise)
    design <- c("Randomized Complete Block (RCB)", "Balanced Incomplete Block Design (BIBD)", 
        "Augmented Randomized Complete Block (ARCB)", "Augmented Latin Square", 
        "Alpha Lattice", "Row-Column")
    if (is.character(data)) {
        data <- eval(parse(text = data))
    }
    if (is.null(env)) {
        env = "EnvLevel"
        data <- cbind(data, EnvLevel = 1)
        addEnv <- TRUE
    }
    else {
        addEnv <- FALSE
    }
    if (exptl.design == "RCB" || exptl.design == "BIBD" || exptl.design == 
        "AugRCB") {
        if (is.na(match(respvar, names(data))) || is.na(match(geno, 
            names(data))) || is.na(match(row, names(data)))) {
            stop("At least one variable name does not match a column in the data frame.")
        }
    }
    if (exptl.design == "AugLS") {
        if (is.na(match(respvar, names(data))) || is.na(match(geno, 
            names(data))) || is.na(match(row, names(data))) || 
            is.na(match(column, names(data)))) {
            stop("At least one variable name does not match a column in the data frame.")
        }
    }
    if (exptl.design == "Alpha") {
        if (is.na(match(respvar, names(data))) || is.na(match(geno, 
            names(data))) || is.na(match(row, names(data))) || 
            is.na(match(rep, names(data)))) {
            stop("At least one variable name does not match a column in the data frame.")
        }
    }
    if (exptl.design == "RowCol") {
        if (is.na(match(respvar, names(data))) || is.na(match(geno, 
            names(data))) || is.na(match(row, names(data))) || 
            is.na(match(column, names(data))) || is.na(match(rep, 
            names(data)))) {
            stop("At least one variable name does not match a column in the data frame.")
        }
    }
    if (is.numeric(data[, match(geno, names(data))])) {
        data[, match(geno, names(data))] <- factor(data[, match(geno, 
            names(data))])
    }
    else {
        if (suppressWarnings(all(!is.na(as.numeric(as.character(factor(trimStrings(data[, 
            match(geno, names(data))])))))))) {
            data[, match(geno, names(data))] <- factor(as.numeric(data[, 
                match(geno, names(data))]))
        }
        else {
            data[, match(geno, names(data))] <- factor(trimStrings(data[, 
                match(geno, names(data))]))
        }
    }
    data[, match(env, names(data))] <- factor(trimStrings(data[, 
        match(env, names(data))]))
    data[, match(row, names(data))] <- factor(trimStrings(data[, 
        match(row, names(data))]))
    if (exptl.design == "AugLS") {
        data[, match(column, names(data))] <- factor(trimStrings(data[, 
            match(column, names(data))]))
    }
    if (exptl.design == "Alpha") {
        data[, match(rep, names(data))] <- factor(trimStrings(data[, 
            match(rep, names(data))]))
    }
    if (exptl.design == "RowCol") {
        data[, match(column, names(data))] <- factor(trimStrings(data[, 
            match(column, names(data))]))
        data[, match(rep, names(data))] <- factor(trimStrings(data[, 
            match(rep, names(data))]))
    }
    result <- list()
    options(width = 5000)
    cat("DESIGN: ", design[match(exptl.design, c("RCB", "BIBD", 
        "AugRCB", "AugLS", "Alpha", "RowCol"))], "\n\n")
    printDataFrame2 <- function(anova.table1) {
        colnames(anova.table1) <- trimStrings(colnames(anova.table1))
        colwidth <- max(c(nchar(trimStrings(rownames(anova.table1), 
            side = "right")), nchar("Source"))) + 2
        for (i in (1:(ncol(anova.table1) - 1))) {
            if (i == 1 || i == 6) {
                colwidth <- c(colwidth, max(c(nchar(anova.table1[!is.na(anova.table1[, 
                  i]), i]), nchar(colnames(anova.table1)[i]))) + 
                  2)
            }
            if (i >= 2 && i <= 4) {
                if (any(anova.table1[, i] < -1)) {
                  colwidth <- c(colwidth, max(c(nchar(round(anova.table1[, 
                    i], 4)), nchar(colnames(anova.table1)[i]))) + 
                    3)
                }
                else {
                  colwidth <- c(colwidth, max(c(nchar(round(anova.table1[, 
                    i], 4)), nchar(colnames(anova.table1)[i]))) + 
                    2)
                }
            }
            if (i == 5) {
                colwidth <- c(colwidth, max(c(nchar(round(anova.table1[!is.na(anova.table1[, 
                  i]), i], 2)), nchar(colnames(anova.table1)[i]))) + 
                  2)
            }
        }
        colwidth <- c(colwidth, 12)
        options(width = 5000)
        cat(formatC(paste(rep("-", sum(colwidth)), collapse = "")), 
            sep = "\n")
        cat(formatC("Source", width = colwidth[1], format = "s", 
            flag = "-"), sep = "")
        for (i in (1:ncol(anova.table1))) {
            cat(formatC(colnames(anova.table1)[i], width = colwidth[(i + 
                1)], format = "s"), sep = "")
        }
        cat("\n")
        cat(formatC(paste(rep("-", sum(colwidth)), collapse = "")), 
            sep = "\n")
        for (i in (1:nrow(anova.table1))) {
            cat(formatC(trimStrings(rownames(anova.table1)[i], 
                side = "right"), width = colwidth[1], format = "s", 
                flag = "-"), sep = "")
            cat(formatC(anova.table1[i, 1], width = colwidth[2]), 
                sep = "")
            for (j in (2:4)) {
                if (!is.na(anova.table1[i, j])) {
                  cat(formatC(anova.table1[i, j], digits = 4, 
                    width = colwidth[j + 1], format = "f"), sep = "")
                }
                else {
                  cat(formatC("", width = colwidth[j + 1], format = "s"), 
                    sep = "")
                }
            }
            if (!is.na(anova.table1[i, 5])) {
                cat(formatC(anova.table1[i, 5], digits = 2, width = colwidth[6], 
                  format = "f"), sep = "")
            }
            else {
                cat(formatC("", width = colwidth[6], format = "s"), 
                  sep = "")
            }
            if (!is.na(anova.table1[i, 6])) {
                cat(formatC(anova.table1[i, 6], width = colwidth[7]), 
                  sep = "")
            }
            else {
                cat(formatC("", width = colwidth[7], format = "s"), 
                  sep = "")
            }
            if (!is.na(anova.table1[i, 7])) {
                cat(formatC(anova.table1[i, 7], digits = 4, width = colwidth[8], 
                  format = "f"), sep = "")
            }
            else {
                cat(formatC("", width = colwidth[8], format = "s"), 
                  sep = "")
            }
            cat("\n")
        }
        cat(formatC(paste(rep("-", sum(colwidth)), collapse = "")), 
            sep = "\n\n")
    }
    for (i in (1:length(respvar))) {
        width1 <- 37 + nchar(respvar)[i]
        cat(paste(rep("=", width1), collapse = ""), "\n")
        cat("ANALYSIS FOR RESPONSE VARIABLE:", respvar[i], "\n")
        cat(paste(rep("=", width1), collapse = ""), "\n\n")
        if (descriptive) {
            if (nlevels(data[, env]) == 1) {
                DescriptiveStatistics(data, var = respvar[i])
            }
            else {
                DescriptiveStatistics(data, var = respvar[i], 
                  grp = env)
            }
            cat("\n")
        }
        result[[i]] <- list()
        for (j in (1:nlevels(data[, match(env, names(data))]))) {
            envLabel <- levels(data[, match(env, names(data))])[j]
            if (!addEnv) {
                width <- 5 + nchar(env) + nchar(envLabel)
                cat(paste(rep("-", width), collapse = ""), "\n")
                cat(env, "=", envLabel, "\n")
                cat(paste(rep("-", width), collapse = ""), "\n\n")
                rm(width)
            }
            temp.data <- data[sort(match(c(respvar[i], geno, 
                row, column, rep, env), names(data)))]
            result[[i]][[j]] <- list()
            result[[i]][[j]]$envLabel <- envLabel
            temp.data <- temp.data[temp.data[, match(env, names(temp.data))] == 
                levels(temp.data[, match(env, names(temp.data))])[j], 
                ]
            if (addEnv) {
                ClassInformation(temp.data[, -I(match(env, names(temp.data)))], 
                  respvar = respvar[i])
            }
            else {
                ClassInformation(temp.data, respvar = respvar[i])
            }
            cat("\n\n")
            no.reps <- data.frame(n = tapply(eval(parse(text = paste("temp.data$", 
                respvar[i], sep = ""))), eval(parse(text = paste("temp.data$", 
                geno, sep = ""))), FUN = length))
            no.reps <- 1/sapply(1/no.reps, mean)
            if (!is.random) {
                if (exptl.design == "RCB" | exptl.design == "BIBD" | 
                  exptl.design == "AugRCB") {
                  myformula1 <- paste(respvar[i], " ~ 1 + ", 
                    geno, " + (1|", row, ")", sep = "")
                }
                if (exptl.design == "AugLS") {
                  myformula1 <- paste(respvar[i], " ~ 1 + ", 
                    geno, " + (1|", row, ") + (1|", column, ")", 
                    sep = "")
                }
                if (exptl.design == "Alpha") {
                  myformula1 <- paste(respvar[i], " ~ 1 + ", 
                    geno, " + (1|", rep, "/", row, ")", sep = "")
                }
                if (exptl.design == "RowCol") {
                  myformula1 <- paste(respvar[i], " ~ 1 + ", 
                    geno, " + (1|", rep, ") + (1|", rep, ":", 
                    row, ") + (1|", rep, ":", column, ")", sep = "")
                }
            }
            if (is.random) {
                if (is.null(exclude)) {
                  if (exptl.design == "RCB" | exptl.design == 
                    "BIBD" | exptl.design == "AugRCB") {
                    myformula1 <- paste(respvar[i], " ~ 1 + (1|", 
                      row, ") + (1|", geno, ")", sep = "")
                  }
                  if (exptl.design == "AugLS") {
                    myformula1 <- paste(respvar[i], " ~ 1 + (1|", 
                      row, ") + (1|", column, ") + (1|", geno, 
                      ")", sep = "")
                  }
                  if (exptl.design == "Alpha") {
                    myformula1 <- paste(respvar[i], " ~ 1 + (1|", 
                      rep, "/", row, ") + (1|", geno, ")", sep = "")
                  }
                  if (exptl.design == "RowCol") {
                    myformula1 <- paste(respvar[i], " ~ 1 + (1|", 
                      rep, ") + (1|", rep, ":", row, ") + (1|", 
                      rep, ":", column, ") + (1|", geno, ")", 
                      sep = "")
                  }
                }
                else {
                  Test <- NULL
                  Check <- NULL
                  for (k in (1:nrow(temp.data))) {
                    if (is.na(match(temp.data[k, match(geno, 
                      names(temp.data))], exclude))) {
                      Test <- c(Test, levels(temp.data[, match(geno, 
                        names(temp.data))])[temp.data[k, match(geno, 
                        names(temp.data))]])
                      Check <- c(Check, "0")
                    }
                    else {
                      Test <- c(Test, "0")
                      Check <- c(Check, exclude[match(temp.data[k, 
                        match(geno, names(temp.data))], exclude)])
                    }
                  }
                  temp.data <- cbind(temp.data, Test, Check)
                  temp.data$Check <- factor(temp.data$Check)
                  temp.data$Test <- factor(temp.data$Test)
                  if (exptl.design == "RCB" | exptl.design == 
                    "BIBD" | exptl.design == "AugRCB") {
                    myformula1 <- paste(respvar[i], " ~ 1 + Check + (1|", 
                      row, ") + (1|Test:Check)", sep = "")
                  }
                  if (exptl.design == "AugLS") {
                    myformula1 <- paste(respvar[i], " ~ 1 + Check + (1|", 
                      row, ") + (1|", column, ") + (1|Test:Check)", 
                      sep = "")
                  }
                  if (exptl.design == "Alpha") {
                    myformula1 <- paste(respvar[i], " ~ 1 + Check + (1|", 
                      rep, "/", row, ") + (1|Test:Check)", sep = "")
                  }
                  if (exptl.design == "RowCol") {
                    myformula1 <- paste(respvar[i], " ~ 1 + Check + (1|", 
                      rep, ") + (1|", rep, ":", row, ") + (1|", 
                      rep, ":", column, ") + (1|Test:Check)", 
                      sep = "")
                  }
                }
            }
            model <- lmer(formula(myformula1), data = temp.data)
            varcomp <- NULL
            for (k in (1:length(VarCorr(model)))) {
                varcomp <- rbind(varcomp, data.frame(Groups = names(VarCorr(model))[k], 
                  Variance = VarCorr(model)[[k]][1], Std.Dev. = attr(VarCorr(model)[[k]], 
                    "stddev")[[1]]))
            }
            varcomp <- rbind(varcomp, data.frame(Groups = "Residual", 
                Variance = attr(VarCorr(model), "sc")^2, Std.Dev. = attr(VarCorr(model), 
                  "sc")))
            attr(varcomp, "heading") <- "Variance Components for Random Effects\n"
            colnames(varcomp)[ncol(varcomp)] <- "StdDev"
            result[[i]][[j]]$varcomp <- varcomp
            if (varComp) {
                cat("Estimates of Variance Components\n")
                printDataFrame(varcomp, digits = 4)
                cat("\n\n")
            }
            if (!is.random) {
                anova.table <- anova(model)
                attr(anova.table, "heading")[1] <- paste("Analysis of Variance for ", 
                  env, " = ", levels(temp.data[, match(env, names(temp.data))])[j], 
                  sep = "")
                attr(anova.table, "heading")[2] <- paste("Response Variable: ", 
                  respvar[i], "\n", sep = "")
                myformula2 <- gsub(paste(" + ", geno, sep = ""), 
                  "", myformula1, fixed = TRUE)
                model1 <- lmer(formula(myformula1), data = temp.data, 
                  REML = F)
                model2 <- lmer(formula(myformula2), data = temp.data, 
                  REML = F)
                anova.table1 <- anova(model2, model1)
                attr(anova.table1, "heading")[1] <- paste("Testing for the Significance of Genotypic Effect\n", 
                  sep = "")
                attr(anova.table1, "heading")[3] <- paste("model2: ", 
                  myformula2, sep = "")
                attr(anova.table1, "heading")[4] <- paste("model1: ", 
                  myformula1, "\n", sep = "")
                result[[i]][[j]]$anova.table <- anova.table1
                cat(attr(anova.table1, "heading")[1], "\n")
                cat(attr(anova.table1, "heading")[2], "\n")
                cat(attr(anova.table1, "heading")[3], "\n")
                cat(attr(anova.table1, "heading")[4], "\n")
                printDataFrame2(anova.table1)
                cat("\n")
                printDataFrame(cbind(Source = geno, anova.table, 
                  Prob = anova.table1[2, 7]))
                cat("\n\n")
                myformula3 <- gsub("~ 1", "~ 0", myformula1)
                model3 <- lmer(formula(myformula3), data = temp.data)
                sumStat.table <- data.frame(summary(model3)@coefs)[, 
                  1:2]
                rownames(sumStat.table) <- gsub(geno, "", rownames(sumStat.table))
                sumStat.table <- cbind(rownames(sumStat.table), 
                  sumStat.table)
                rownames(sumStat.table) <- NULL
                colnames(sumStat.table) <- c(geno, "LSMean", 
                  "StdErrMean")
                result[[i]][[j]]$summaryTable <- sumStat.table
                cat(geno, "LSMeans and Standard Error\n")
                printDataFrame(sumStat.table)
                cat("\n\n")
                if (pairwise != "none") {
                  if (pairwise == "Dunnett" && length(exclude) != 
                    0) {
                    initialOutput <- TRUE
                    for (k in (1:length(exclude))) {
                      resultpw <- try(pwresult <- ssa.pairwise(model, 
                        "Dunnett", alpha, exclude[k]), silent = TRUE)
                      if (class(resultpw) == "try-error") {
                        cat("***\n", resultpw, "***\n\n", sep = "")
                        break
                      }
                      if (initialOutput) {
                        cat("List of", geno, "pairs that are significantly different using", 
                          pairwise, "at alpha =", alpha, "\n\n")
                      }
                      cat("Compare with control = ", exclude[k], 
                        "\n")
                      if (!is.null(nrow(pwresult))) {
                        colnames(pwresult)[1:2] <- c(geno, "Control")
                        printDataFrame(pwresult)
                        cat("\n")
                      }
                      else {
                        cat(resultpw, "\n\n")
                      }
                      initialOutput <- FALSE
                    }
                  }
                  else {
                    resultpw <- try(pwresult <- ssa.pairwise(model, 
                      "Tukey", alpha), silent = TRUE)
                    if (class(resultpw) == "try-error") {
                      cat("***\n", resultpw, "***\n\n", sep = "")
                      break
                    }
                    if (!is.null(nrow(pwresult))) {
                      cat("List of", geno, "pairs that are significantly different using", 
                        pairwise, "at alpha =", alpha, "\n")
                      colnames(pwresult)[1:2] <- c(paste(geno, 
                        "[i]", sep = ""), paste(geno, "[j]", 
                        sep = ""))
                      printDataFrame(pwresult)
                    }
                    else {
                      cat(resultpw, "\n")
                    }
                  }
                }
                if (j == 1) {
                  tempsummaryStat <- cbind(envLabel, sumStat.table, 
                    sigma2 = attr(VarCorr(model), "sc")^2, no.reps[[1]])
                }
                else {
                  tempsummaryStat <- rbind(tempsummaryStat, cbind(envLabel, 
                    sumStat.table, sigma2 = attr(VarCorr(model), 
                      "sc")^2, no.reps[[1]]))
                }
            }
            if (is.random) {
                if (is.null(exclude)) {
                  myformula2 <- gsub(paste(" + (1|", geno, ")", 
                    sep = ""), "", myformula1, fixed = TRUE)
                }
                else {
                  myformula2 <- gsub("+ (1|Test:Check)", "", 
                    myformula1, fixed = TRUE)
                }
                model1 <- lmer(formula(myformula1), data = temp.data, 
                  REML = F)
                model2 <- lmer(formula(myformula2), data = temp.data, 
                  REML = F)
                anova.table1 <- anova(model2, model1)
                attr(anova.table1, "heading")[3] <- paste("model2: ", 
                  myformula2, sep = "")
                attr(anova.table1, "heading")[4] <- paste("model1: ", 
                  myformula1, "\n", sep = "")
                attr(anova.table1, "heading")[1] <- paste("Linear Mixed Model fit by Log Likelihood Ratio Test", 
                  sep = "")
                anova.table2 <- anova.table1[2, c(5, ncol(anova.table1))]
                rownames(anova.table2) <- geno
                attr(anova.table2, "heading")[1] <- attr(anova.table1, 
                  "heading")[1]
                attr(anova.table2, "heading")[2] <- paste("Environment Variable: ", 
                  env, " = ", levels(temp.data[, match(env, names(temp.data))])[j], 
                  sep = "")
                attr(anova.table2, "heading")[3] <- paste("Response Variable: ", 
                  respvar[i], "\n", sep = "")
                result[[i]][[j]]$anova.table <- anova.table2
                cat("Test of the Significance of", geno, "Effect\n")
                cat(attr(anova.table2, "heading")[1], "\n")
                if (!addEnv) {
                  cat(attr(anova.table2, "heading")[2], "\n")
                }
                cat(attr(anova.table2, "heading")[3], "\n")
                printDataFrame(cbind(Source = rownames(anova.table2), 
                  anova.table2))
                cat("\n\n")
                if (is.null(exclude)) {
                  sumStat.table <- eval(parse(text = paste("coef(model)$", 
                    geno, sep = "")))
                  sumStat.table <- cbind(rownames(sumStat.table), 
                    sumStat.table)
                }
                else {
                  sumStat.table <- coef(model)$"Test:Check"
                  temp.names <- t(as.data.frame(strsplit(rownames(sumStat.table), 
                    ":")))
                  sumStat.table$Test <- temp.names[, 1]
                  sumStat.table$Check <- temp.names[, 2]
                  sumStat.table <- subset(sumStat.table, Test != 
                    "0", select = c("Test", "(Intercept)"))
                }
                rownames(sumStat.table) <- NULL
                colnames(sumStat.table) <- c(geno, "Means")
                attr(sumStat.table, "heading") <- paste("Predicted Means of ", 
                  geno, "\n", sep = "")
                result[[i]][[j]]$PredictedMeans <- sumStat.table
                cat(geno, "Predicted Means\n")
                printDataFrame(sumStat.table)
                cat("\n\n")
                if (is.null(exclude)) {
                  herit <- varcomp[varcomp[, 1] == geno, "Variance"]/(varcomp[varcomp[, 
                    1] == geno, "Variance"] + (varcomp[varcomp[, 
                    1] == "Residual", "Variance"]/no.reps))
                }
                else {
                  herit <- varcomp[varcomp[, 1] == "Test:Check", 
                    "Variance"]/(varcomp[varcomp[, 1] == "Test:Check", 
                    "Variance"] + (varcomp[varcomp[, 1] == "Residual", 
                    "Variance"]/no.reps))
                }
                result[[i]][[j]]$heritability <- herit[[1]]
                cat("Estimate of Heritability\n")
                if (herit[[1]] > 0.001) {
                  cat("Heritability =", round(herit[[1]], digits = 2), 
                    "\n\n")
                }
                else {
                  cat("Heritability =", round(herit[[1]], digits = 4), 
                    "\n\n")
                }
                if (j == 1) {
                  tempsummaryStat <- cbind(envLabel, sumStat.table)
                }
                else {
                  tempsummaryStat <- rbind(tempsummaryStat, cbind(envLabel, 
                    sumStat.table))
                }
            }
            if (j == 1) {
                tempnewdata <- cbind(temp.data[!is.na(temp.data[, 
                  respvar[i]]), ], resid(model1), fitted(model1))
            }
            else {
                tempnewdata <- rbind(tempnewdata, cbind(temp.data[!is.na(temp.data[, 
                  respvar[i]]), ], resid(model1), fitted(model1)))
            }
            if (diagPlot) {
                if (is.null(outputPath)) {
                  outputPath <- getwd()
                }
                if (is.random) {
                  factorType <- "random"
                }
                else {
                  factorType <- "fixed"
                }
                if (!addEnv) {
                  xlabel <- paste(respvar[i], ": ", env, " = ", 
                    envLabel, sep = "")
                  pathTemp <- paste(outputPath, "/diagPlotsSEA_", 
                    respvar[i], "_", envLabel, ".png", sep = "")
                }
                else {
                  xlabel <- respvar[i]
                  pathTemp <- paste(outputPath, "/diagPlotsSEA_", 
                    respvar[i], ".png", sep = "")
                }
                png(pathTemp)
                par(mfrow = c(2, 2))
                plot(resid(model1) ~ fitted(model1), xlab = "Predicted Values", 
                  pch = 15, cex = 0.7, col = rgb(0.3, 0.3, 0.7, 
                    0.2), ylab = "Residuals", main = xlabel)
                qqnorm(resid(model1))
                qqline(resid(model1), col = 2, main = title, 
                  sub = xlabel)
                hist(resid(model), main = "Residuals", col = rgb(0.3, 
                  0.3, 0.7, 0.2), xlab = "Residual", ylab = "Frequency")
                dev.off()
            }
        }
        if (is.random) {
            colnames(tempsummaryStat) <- c(env, geno, paste(respvar[i], 
                "PredMean", sep = "_"))
            colnames(tempnewdata)[(ncol(tempnewdata) - 1):ncol(tempnewdata)] <- paste(respvar[i], 
                c("resid", "fitted"), "random", sep = "_")
        }
        else {
            colnames(tempsummaryStat) <- c(env, geno, paste(respvar[i], 
                c("Mean", "StdErrMean"), sep = "_"), paste(respvar[i], 
                c("sigma2", "numReps"), sep = "_"))
            colnames(tempnewdata)[(ncol(tempnewdata) - 1):ncol(tempnewdata)] <- paste(respvar[i], 
                c("resid", "fitted"), sep = "_")
        }
        if (i == 1) {
            summaryStat <- tempsummaryStat
            newData <- tempnewdata
        }
        else {
            summaryStat <- merge(summaryStat, tempsummaryStat, 
                all = TRUE)
            newData <- merge(newData, tempnewdata, all = TRUE)
        }
    }
    return(list(design = design[match(exptl.design, c("RCB", 
        "BIBD", "AugRCB", "AugLS", "Alpha", "RowCol"))], summary.statistics = summaryStat, 
        residData = newData, result = result))
}
