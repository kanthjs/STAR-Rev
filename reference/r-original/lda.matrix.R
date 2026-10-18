`lda.matrix` <-
function (x, grouping, ..., subset, na.action) 
{
    if (!missing(subset)) {
        x <- x[subset, , drop = FALSE]
        grouping <- grouping[subset]
    }
    if (!missing(na.action)) {
        dfr <- na.action(structure(list(g = grouping, x = x), 
            class = "data.frame"))
        grouping <- dfr$g
        x <- dfr$x
    }
    res <- lda.default(x, grouping, ...)
    cl <- match.call()
    cl[[1L]] <- as.name("lda")
    res$call <- cl
    res
}
