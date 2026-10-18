`print.lda` <-
function (x, ...) 
{
    if (!is.null(cl <- x$call)) {
        names(cl)[2L] <- ""
        cat("Call:\n")
        dput(cl, control = NULL)
    }
    cat("\nPrior probabilities of groups:\n")
    print(x$prior, ...)
    cat("\nGroup means:\n")
    print(x$means, ...)
    cat("\nCoefficients of linear discriminants:\n")
    print(x$scaling, ...)
    svd <- x$svd
    names(svd) <- dimnames(x$scaling)[[2L]]
    if (length(svd) > 1L) {
        cat("\nProportion of trace:\n")
        print(round(svd^2/sum(svd^2), 4L), ...)
    }
    invisible(x)
}
