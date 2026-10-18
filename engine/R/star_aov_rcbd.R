## New API (not part of the original STAR): structured results for a one-factor RCBD analysis.
## The original functions print text; these wrap them and return data frames so that an app can
## render tables, draw plots and export to Excel.

star_aov_rcbd <- function(data, response, treatment, block,
                          test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe"), alpha = 0.05) {
  test <- match.arg(test)
  if (!is.data.frame(data)) stop("'data' must be a data frame.")
  for (v in c(response, treatment, block)) if (!v %in% names(data)) stop("Column '", v, "' not found.")
  if (length(unique(c(response, treatment, block))) < 3) stop("Response, treatment and block must be three different columns.")
  if (!is.numeric(data[[response]])) stop("The response variable must be numeric.")
  d <- data[, c(block, treatment, response)]
  d[[block]] <- factor(d[[block]])
  d[[treatment]] <- factor(d[[treatment]])

  if (test == "auto") test <- if (nlevels(d[[treatment]]) <= 5) "LSD" else "HSD"
  report <- capture.output(res <- AOVTest(data = d, design = "RCBD", respvar = response, pwTest = test, pwVar = response,
                                          factor1 = treatment, rep1 = block, sig = alpha))
  raw <- ConstructAOVTable(res$aovTable[[1]][[1]])
  anova <- data.frame(Source = trimws(rownames(raw)), DF = raw[[1]], SS = raw[[2]], MS = raw[[3]],
                      F = raw[[4]], P = raw[[5]], row.names = NULL, stringsAsFactors = FALSE)
  err <- anova[anova$Source == "Error", ]
  p_trt <- anova$P[anova$Source == treatment]

  y <- d[[response]]
  grand_mean <- mean(y, na.rm = TRUE)
  cv <- 100 * sqrt(err$MS) / grand_mean

  fn <- get(paste0(test, ".test"), mode = "function")
  ok <- !is.na(y)
  invisible(capture.output(cmp <- fn(y[ok], d[[treatment]][ok], err$DF, err$MS, alpha)))
  s <- cmp$summary
  means <- data.frame(Treatment = as.character(s[[1]]), Mean = s$means, N = s$N, SE = s$std.err,
                      Group = trimws(as.character(s$group)), stringsAsFactors = FALSE)
  means <- means[match(levels(d[[treatment]]), means$Treatment), ]
  rownames(means) <- NULL
  significant <- length(p_trt) == 1 && !is.na(p_trt) && p_trt < alpha

  summary_text <- if (significant) {
    best <- means$Treatment[which.max(means$Mean)]
    sprintf("%s has a significant effect on %s (F = %.2f, p = %s at alpha = %s). The highest mean is %s (%s). Means sharing a letter are not significantly different (%s test). CV = %.1f%%.",
            treatment, response, anova$F[anova$Source == treatment], format.pval(p_trt, digits = 3), alpha,
            best, format(round(max(means$Mean), 2), nsmall = 2), test, cv)
  } else {
    sprintf("No significant effect of %s on %s (F = %.2f, p = %s at alpha = %s). CV = %.1f%%. Mean separation letters are not meaningful.",
            treatment, response, anova$F[anova$Source == treatment], format.pval(p_trt, digits = 3), alpha, cv)
  }

  structure(list(anova = anova, means = means, grand_mean = grand_mean, cv = cv, test = test, alpha = alpha,
                 response = response, treatment = treatment, block = block, p_treatment = p_trt,
                 significant = significant, summary = summary_text, report = report),
            class = "star_aov_rcbd")
}

print.star_aov_rcbd <- function(x, ...) {
  cat(x$summary, "\n\nANOVA\n")
  print(x$anova, row.names = FALSE, digits = 4)
  cat("\nMeans (", x$test, ")\n", sep = "")
  print(x$means, row.names = FALSE, digits = 4)
  invisible(x)
}

## Bar plot of treatment means with +/- 1 SE bars and mean-separation letters.
star_plot_means <- function(x, main = NULL, col = "#4C78A8") {
  m <- x$means
  top <- max(m$Mean + m$SE, na.rm = TRUE)
  op <- par(mar = c(5, 5, 3, 1)); on.exit(par(op))
  mid <- barplot(m$Mean, names.arg = m$Treatment, col = col, border = NA, ylim = c(0, top * 1.15),
                 xlab = x$treatment, ylab = paste("Mean", x$response),
                 main = if (is.null(main)) paste("Mean", x$response, "by", x$treatment) else main)
  arrows(mid, m$Mean - m$SE, mid, m$Mean + m$SE, angle = 90, code = 3, length = 0.05)
  if (x$significant) text(mid, m$Mean + m$SE, labels = m$Group, pos = 3, cex = 1.1)
  mtext(sprintf("Bars: mean +/- SE. %s", if (x$significant) paste("Letters:", x$test, "test, alpha =", x$alpha) else "Treatment effect not significant"),
        side = 3, line = 0, cex = 0.8, col = "grey40")
  invisible(mid)
}
