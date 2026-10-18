`print.durbinWatsonTest` <-
function (x, ...) 
{
    max.lag <- length(x$dw)
    result <- if (is.null(x$p)) 
        cbind(lag = 1:max.lag, Autocorrelation = x$r, `D-W Statistic` = x$dw)
    else cbind(lag = 1:max.lag, Autocorrelation = x$r, `D-W Statistic` = x$dw, 
        `p-value` = x$p)
    rownames(result) <- rep("", max.lag)
    print(result)
    cat(paste(" Alternative hypothesis: rho", if (max.lag > 1) 
        "[lag]"
    else "", c(" != ", " > ", " < ")[which(x$alternative == c("two.sided", 
        "positive", "negative"))], "0\n", sep = ""))
    invisible(x)
}
