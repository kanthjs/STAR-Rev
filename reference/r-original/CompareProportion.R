`CompareProportion` <-
function (data, varX, varY = NULL, grp = NULL, testVal = NULL, 
    procedure = c("one", "paired", "independent"), alternative = c("two.sided", 
        "greater", "less"), CI = FALSE, confLevel = 0.95) 
UseMethod("CompareProportion")
