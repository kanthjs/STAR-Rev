`ClusterKmeans` <-
function (data, var, idVar = NULL, clusterMem = TRUE, descriptiveStatRaw = TRUE, 
    corMatx = TRUE, descriptiveStat = TRUE, kgraph = TRUE, clusterNum = 2, 
    saveMem = TRUE, outputPath = NULL) 
UseMethod("ClusterKmeans")
