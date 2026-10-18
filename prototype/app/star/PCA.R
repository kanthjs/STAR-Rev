`PCA` <-
function (data, var, idVar = NULL, descriptiveStat = TRUE, corMatx = TRUE, 
    covMatx = TRUE, matx = c("corr", "cov"), transform = c("zerocenter", 
        "unitvar", "none"), saveScore = TRUE, outputPath = NULL, 
    scatterMatx = TRUE, scree = TRUE, biplot = TRUE, pcaplot = TRUE, 
    useIdVar = FALSE, pChars, pSizes, pCol, showLeg = FALSE, 
    legTitle, legPos = "bottomright", legNcol = 1, axesNum = 3) 
UseMethod("PCA")
