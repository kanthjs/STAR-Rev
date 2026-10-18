`plot.lda` <-
function (x, panel = panel.lda, ..., cex = 0.7, dimen, abbrev = FALSE, 
    xlab = "LD1", ylab = "LD2") 
{
    panel.lda <- function(x, y, ...) text(x, y, as.character(g), 
        cex = cex, ...)
    if (!is.null(Terms <- x$terms)) {
        data <- model.frame(x)
        X <- model.matrix(delete.response(Terms), data)
        g <- model.response(data)
        xint <- match("(Intercept)", colnames(X), nomatch = 0L)
        if (xint > 0L) 
            X <- X[, -xint, drop = FALSE]
    }
    else {
        xname <- x$call$x
        gname <- x$call[[3L]]
        if (!is.null(sub <- x$call$subset)) {
            X <- eval.parent(parse(text = paste(deparse(xname, 
                backtick = TRUE), "[", deparse(sub, backtick = TRUE), 
                ",]")))
            g <- eval.parent(parse(text = paste(deparse(gname, 
                backtick = TRUE), "[", deparse(sub, backtick = TRUE), 
                "]")))
        }
        else {
            X <- eval.parent(xname)
            g <- eval.parent(gname)
        }
        if (!is.null(nas <- x$call$na.action)) {
            df <- data.frame(g = g, X = X)
            df <- eval(call(nas, df))
            g <- df$g
            X <- df$X
        }
    }
    if (abbrev) 
        levels(g) <- abbreviate(levels(g), abbrev)
    means <- colMeans(x$means)
    X <- scale(X, center = means, scale = FALSE) %*% x$scaling
    if (!missing(dimen) && dimen < ncol(X)) 
        X <- X[, 1L:dimen, drop = FALSE]
    if (ncol(X) > 2L) {
        pairs(X, panel = panel, ...)
    }
    else if (ncol(X) == 2L) {
        eqscplot(X[, 1L:2L], xlab = xlab, ylab = ylab, type = "n", 
            ...)
        panel(X[, 1L], X[, 2L], ...)
    }
    else ldahist(X[, 1L], g, xlab = xlab, ...)
    invisible(NULL)
}
