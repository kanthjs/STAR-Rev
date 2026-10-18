`MDS` <-
function (data, outputPath, inputType = c("raw", "distMat"), 
    vars = NULL, idVar, type = c("Classical", "Nonmetric"), distClass = c("Euclidean", 
        "Maximum", "Manhattan", "Canberra", "Minkowski"), distNonmet = c("Manhattan", 
        "Euclidean", "Canberra", "Bray", "Kulczynski", "Jaccard", 
        "Gower", "AltGower", "Morisita", "Horn", "Mountford", 
        "Raup", "Binomial", "Chao", "Cao"), dimnum = 2, useIdVar = FALSE, 
    pChars, pSizes, pCol, showLeg = FALSE, legTitle, legPos = "bottomright", 
    legNcol = 1, descriptive = FALSE, correlate = FALSE) 
UseMethod("MDS")
