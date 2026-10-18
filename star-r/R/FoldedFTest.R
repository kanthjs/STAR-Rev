`FoldedFTest` <-
function (x, grp) 
{
    result <- rbind(tapply(x, grp, var), tapply(x, grp, length))
    result <- result[, order(result[1, ])]
    fc = result[1, ncol(result)][[1]]/result[1, 1][[1]]
    pval <- 2 * pf(fc, result[2, ncol(result)][[1]] - 1, result[2, 
        1][[1]] - 1, lower.tail = FALSE)
    df <- c(NUMDF = result[2, ncol(result)][[1]] - 1, DENDF = result[2, 
        1][[1]] - 1)
    return(list(statistic = fc[[1]], df = c(num.df = result[2, 
        ncol(result)][[1]] - 1, denom.df = result[2, 1][[1]] - 
        1), pvalue = pval[[1]], method = "Folded F"))
}
