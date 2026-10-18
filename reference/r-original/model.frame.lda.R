`model.frame.lda` <-
function (formula, ...) 
{
    oc <- formula$call
    oc$prior <- oc$tol <- oc$method <- oc$CV <- oc$nu <- NULL
    oc[[1L]] <- quote(stats::model.frame)
    if (length(dots <- list(...))) {
        nargs <- dots[match(c("data", "na.action", "subset"), 
            names(dots), 0L)]
        oc[names(nargs)] <- nargs
    }
    if (is.null(env <- environment(formula$terms))) 
        env <- parent.frame()
    eval(oc, env)
}
