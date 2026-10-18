`ClusterDivisive` <-
function (data, var = NULL, idVar = NULL, sbinVar = NULL, abinVar = NULL, 
    ofactorVar = NULL, factorVar = NULL, stand = TRUE, distance = c("Euclidean", 
        "Maximum", "Manhattan", "Minkowski", "Canberra", "Binary", 
        "Simple Matching", "Sokal & Sneath", "Hamann coefficient", 
        "Jaccard", "Dice", "Gower"), distMatrix = FALSE, copDistance = FALSE, 
    clusterMem = TRUE, descriptiveStatRaw = TRUE, corMatx = TRUE, 
    scatterMatx = TRUE, descriptiveStat = TRUE, dendrogram = TRUE, 
    clusterBox = TRUE, clusterNum = 2, saveMem = TRUE, outputPath = NULL) 
UseMethod("ClusterDivisive")
