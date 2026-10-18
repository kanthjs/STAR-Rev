`designSplit` <-
function (main, sub, ssub = NULL, sssub = NULL, r = NULL, trial = 1, 
    design = c("crd", "rcbd", "lsd"), numFieldRow = 1, rowPerBlk = 1, 
    rowPerMain = 1, rowPerSub = 1, rowPerSubSub = 1, serpentine = FALSE, 
    display = TRUE, file = NULL) 
UseMethod("designSplit")
