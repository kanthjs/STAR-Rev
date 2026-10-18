`ldahist` <-
function (data, g, nbins = 25, h, x0 = -h/1000, breaks, xlim = range(breaks), 
    ymax = 0, width, type = c("histogram", "density", "both"), 
    sep = (type != "density"), col = 5L, xlab = deparse(substitute(data)), 
    bty = "n", ...) 
{
    xlab
    type <- match.arg(type)
    data <- data[!is.na(data)]
    g <- g[!is.na(data)]
    counts <- table(g)
    groups <- names(counts)[counts > 0L]
    if (missing(breaks)) {
        if (missing(h)) 
            h <- diff(pretty(data, nbins))[1L]
        first <- floor((min(data) - x0)/h)
        last <- ceiling((max(data) - x0)/h)
        breaks <- x0 + h * c(first:last)
    }
    if (type == "histogram" || type == "both") {
        if (any(diff(breaks) <= 0)) 
            stop("'breaks' must be strictly increasing")
        if (min(data) < min(breaks) || max(data) > max(breaks)) 
            stop("'breaks' do not cover the data")
        est <- vector("list", length(groups))
        names(est) <- groups
        for (grp in groups) {
            bin <- cut(data[g == grp], breaks, include.lowest = TRUE)
            est1 <- tabulate(bin, length(levels(bin)))
            est1 <- est1/(diff(breaks) * length(data[g == grp]))
            ymax <- max(ymax, est1)
            est[[grp]] <- est1
        }
    }
    if (type == "density" || type == "both") {
        xd <- vector("list", length(groups))
        for (grp in groups) {
            if (missing(width)) 
                width <- width.SJ(data[g == grp])
            xd1 <- density(data[g == grp], n = 200L, width = width, 
                from = xlim[1L], to = xlim[2L])
            ymax <- max(ymax, xd1$y)
            xd[[grp]] <- xd1
        }
    }
    dev.hold()
    on.exit(dev.flush())
    if (!sep) 
        plot(xlim, c(0, ymax), type = "n", xlab = xlab, ylab = "", 
            bty = bty)
    else {
        oldpar <- par(mfrow = c(length(groups), 1L))
        on.exit(par(oldpar), add = TRUE)
    }
    for (grp in groups) {
        if (sep) 
            plot(xlim, c(0, ymax), type = "n", xlab = paste("group", 
                grp), ylab = "", bty = bty)
        if (type == "histogram" || type == "both") {
            n <- length(breaks)
            rect(breaks[-n], 0, breaks[-1L], est[[grp]], col = col, 
                ...)
        }
        if (type == "density" || type == "both") 
            lines(xd[[grp]])
    }
    invisible()
}
