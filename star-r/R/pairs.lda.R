`pairs.lda` <-
function (x, labels = colnames(x), panel = panel.lda, dimen, 
    abbrev = FALSE, ..., cex = 0.7, type = c("std", "trellis")) 
{
    panel.lda <- function(x, y, ...) text(x, y, as.character(g), 
        cex = cex, ...)
    type <- match.arg(type)
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
    g <- as.factor(g)
    if (abbrev) 
        levels(g) <- abbreviate(levels(g), abbrev)
    means <- colMeans(x$means)
    X <- scale(X, center = means, scale = FALSE) %*% x$scaling
    if (!missing(dimen) && dimen < ncol(X)) 
        X <- X[, 1L:dimen]
    if (type == "std") 
        pairs(X, panel = panel, ...)
    else {
        print(lattice::splom(~X, groups = g, panel = lattice::panel.superpose, 
            key = list(text = list(levels(g)), points = lattice::Rows(lattice::trellis.par.get("superpose.symbol"), 
                seq_along(levels(g))), columns = min(5L, length(levels(g))))))
    }
    invisible(NULL)
}
