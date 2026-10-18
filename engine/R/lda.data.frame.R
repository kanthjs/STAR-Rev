`lda.data.frame` <-
function (x, ...) 
{
    res <- lda(structure(data.matrix(x), class = "matrix"), ...)
    cl <- match.call()
    cl[[1L]] <- as.name("lda")
    res$call <- cl
    res
}
