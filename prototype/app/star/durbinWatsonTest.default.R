`durbinWatsonTest.default` <-
function (model, max.lag = 1, ...) 
{
    if ((!is.vector(model)) || (!is.numeric(model))) 
        stop("requires vector of residuals")
    if (any(is.na(model))) 
        stop("residuals include missing values")
    n <- length(model)
    dw <- rep(0, max.lag)
    den <- sum(model^2)
    for (lag in 1:max.lag) {
        dw[lag] <- (sum((model[(lag + 1):n] - model[1:(n - lag)])^2))/den
    }
    dw
}
