## New API (not part of the original STAR): ggplot2 plots for star_correlation() and star_regression().
## Each function returns a ggplot object, so it can be printed, modified with `+`, or saved with star_ggsave().
##   star_ggplot_correlation(x, pair)     heatmap of the correlation matrix, or the scatter plot of one pair
##   star_ggplot_regression(x)            data with the fitted line / curve and its confidence band; for several
##                                        predictors, observed against fitted values

.star_stars <- function(p, alpha) ifelse(is.na(p), "", ifelse(p < 0.001, "***", ifelse(p < 0.01, "**", ifelse(p < alpha, "*", ""))))

star_ggplot_correlation <- function(x, pair = NULL, main = NULL, xlab = NULL, ylab = NULL, base_size = 12) {
  if (!inherits(x, "star_correlation")) stop("'x' must be a result of star_correlation().", call. = FALSE)
  if (is.null(pair)) {
    v <- x$vars
    k <- length(v)
    g <- expand.grid(i = seq_len(k), j = seq_len(k))
    g <- g[g$i > g$j, ]                                  # lower triangle: each pair once
    g$r <- x$r[cbind(g$i, g$j)]
    g$p <- x$p[cbind(g$i, g$j)]
    g$lab <- paste0(formatC(g$r, format = "f", digits = 2), .star_stars(g$p, x$alpha))
    g$x <- factor(v[g$j], levels = v)
    g$y <- factor(v[g$i], levels = rev(v))
    return(ggplot2::ggplot(g, ggplot2::aes(x = x, y = y, fill = r)) +
      ggplot2::geom_tile(colour = "white", linewidth = 1) +
      ggplot2::geom_text(ggplot2::aes(label = lab), fontface = "bold", size = 4) +
      ggplot2::scale_fill_gradient2(low = "#D55E00", mid = "white", high = "#0072B2", midpoint = 0, limits = c(-1, 1),
                                    name = x$symbol) +
      ggplot2::scale_x_discrete(drop = FALSE, limits = v[-k]) +
      ggplot2::scale_y_discrete(drop = FALSE, limits = rev(v[-1])) +
      ggplot2::coord_fixed() +
      ggplot2::labs(x = .star_gg_or(xlab, NULL), y = .star_gg_or(ylab, NULL), title = .star_gg_or(main, paste(x$method_name, "correlation")),
                    subtitle = sprintf("* p < %s, ** p < 0.01, *** p < 0.001%s", format(x$alpha), if (x$adjust != "none") paste0(" (", x$adjust, "-adjusted)") else "")) +
      star_gg_theme(base_size) + ggplot2::theme(legend.position = "right", axis.line = ggplot2::element_blank(),
                                                axis.ticks = ggplot2::element_blank()))
  }
  if (length(pair) != 2 || !all(pair %in% x$vars)) stop("'pair' must name two of the correlated variables.", call. = FALSE)
  d <- x$data[stats::complete.cases(x$data[pair]), pair, drop = FALSE]
  names(d) <- c("x", "y")
  row <- x$pairs[(x$pairs$Variable1 == pair[1] & x$pairs$Variable2 == pair[2]) | (x$pairs$Variable1 == pair[2] & x$pairs$Variable2 == pair[1]), ]
  p <- ggplot2::ggplot(d, ggplot2::aes(x = x, y = y))
  if (x$method == "pearson") p <- p + ggplot2::geom_smooth(method = "lm", formula = y ~ x, colour = "#D55E00", fill = "#D55E00", alpha = 0.15, linewidth = 0.9)
  p + ggplot2::geom_point(shape = 21, size = 2.4, fill = "#0072B2", colour = "grey20", alpha = 0.85) +
    ggplot2::labs(x = .star_gg_or(xlab, pair[1]), y = .star_gg_or(ylab, pair[2]), title = .star_gg_or(main, paste(pair[2], "against", pair[1])),
                  subtitle = sprintf("%s = %.3f, p %s, n = %d", x$symbol, row$r, .star_fmt_p(row$PAdj), row$N)) +
    star_gg_theme(base_size)
}

star_ggplot_regression <- function(x, level = 0.95, main = NULL, xlab = NULL, ylab = NULL, base_size = 12) {
  if (!inherits(x, "star_regression")) stop("'x' must be a result of star_regression().", call. = FALSE)
  sub <- sprintf("%s;  R-squared = %.3f, p %s", x$equation, x$fit$r2, .star_fmt_p(x$p_model))
  if (length(x$predictors) == 1) {
    v <- x$predictors
    pts <- data.frame(x = x$data[[v]], y = x$data[[x$response]])
    grid <- data.frame(seq(min(pts$x), max(pts$x), length.out = 120)); names(grid) <- v
    pr <- star_predict(x, grid, level)
    ln <- data.frame(x = grid[[v]], y = pr$Predicted, lo = pr$Lower, hi = pr$Upper)
    p <- ggplot2::ggplot(pts, ggplot2::aes(x = x, y = y)) +
      ggplot2::geom_ribbon(data = ln, ggplot2::aes(y = y, ymin = lo, ymax = hi), fill = "#D55E00", alpha = 0.15) +
      ggplot2::geom_line(data = ln, colour = "#D55E00", linewidth = 1) +
      ggplot2::geom_point(shape = 21, size = 2.4, fill = "#0072B2", colour = "grey20", alpha = 0.85)
    if (!is.null(x$optimum) && x$optimum$inside) {
      o <- data.frame(x = x$optimum$x, y = x$optimum$y, lab = sprintf("%s at %s", x$optimum$type, .star_fmt_num(x$optimum$x)))
      p <- p + ggplot2::geom_point(data = o, shape = 23, size = 4, fill = "#F0E442", colour = "grey20") +
        ggplot2::geom_text(data = o, ggplot2::aes(label = lab), vjust = -1.2, fontface = "bold")
    }
    return(p + ggplot2::labs(x = .star_gg_or(xlab, v), y = .star_gg_or(ylab, x$response), title = .star_gg_or(main, paste(x$response, "against", v)),
                             subtitle = paste0(sub, sprintf("\nBand: %g%% confidence interval of the mean", 100 * level))) +
             star_gg_theme(base_size))
  }
  pts <- data.frame(x = stats::fitted(x$model), y = x$data[[x$response]])
  ggplot2::ggplot(pts, ggplot2::aes(x = x, y = y)) +
    ggplot2::geom_abline(slope = 1, intercept = 0, colour = "#D55E00", linewidth = 0.9) +
    ggplot2::geom_point(shape = 21, size = 2.4, fill = "#0072B2", colour = "grey20", alpha = 0.85) +
    ggplot2::labs(x = .star_gg_or(xlab, paste("Fitted", x$response)), y = .star_gg_or(ylab, paste("Observed", x$response)),
                  title = .star_gg_or(main, paste("Observed against fitted", x$response)),
                  subtitle = sprintf("R-squared = %.3f, p %s. Line: observed = fitted", x$fit$r2, .star_fmt_p(x$p_model))) +
    star_gg_theme(base_size)
}
