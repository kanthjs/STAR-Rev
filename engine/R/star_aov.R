## New API (not part of the original STAR): structured results for a one-factor analysis of variance.
## The original functions print text; these wrap them and return data frames so that an app can
## render tables, draw plots and export to Excel.
##   design = "CRD"  : completely randomized design (no blocks)
##   design = "RCBD" : randomized complete block design (`block` required)

star_aov <- function(data, response, treatment, block = NULL, design = c("CRD", "RCBD"),
                     test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe"), alpha = 0.05) {
  design <- match.arg(design)
  test <- match.arg(test)
  if (!is.data.frame(data)) stop("'data' must be a data frame.")
  if (design == "RCBD" && (is.null(block) || !nzchar(block))) stop("An RCBD analysis needs a block column.")
  if (design == "CRD") block <- NULL
  cols <- c(response, treatment, block)
  for (v in cols) if (!v %in% names(data)) stop("Column '", v, "' not found.")
  if (length(unique(cols)) < length(cols)) stop("Response, treatment and block must be different columns.")
  if (!is.numeric(data[[response]])) stop("The response variable must be numeric.")
  d <- data[, cols, drop = FALSE]
  d[[treatment]] <- factor(d[[treatment]])
  if (!is.null(block)) d[[block]] <- factor(d[[block]])
  if (nlevels(d[[treatment]]) < 2) stop("The treatment column needs at least two levels.")

  if (test == "auto") test <- if (nlevels(d[[treatment]]) <= 5) "LSD" else "HSD"
  report <- capture.output(res <- if (design == "RCBD") {
    AOVTest(data = d, design = "RCBD", respvar = response, pwTest = test, pwVar = response,
            factor1 = treatment, rep1 = block, sig = alpha)
  } else {
    AOVTest(data = d, design = "CRD", respvar = response, pwTest = test, pwVar = response,
            factor1 = treatment, sig = alpha)
  })
  raw <- ConstructAOVTable(res$aovTable[[1]][[1]])
  anova <- data.frame(Source = trimws(rownames(raw)), DF = raw[[1]], SS = raw[[2]], MS = raw[[3]],
                      F = raw[[4]], P = raw[[5]], row.names = NULL, stringsAsFactors = FALSE)
  err <- anova[anova$Source == "Error", ]
  p_trt <- anova$P[anova$Source == treatment]
  f_trt <- anova$F[anova$Source == treatment]

  y <- d[[response]]
  grand_mean <- mean(y, na.rm = TRUE)
  cv <- 100 * sqrt(err$MS) / grand_mean

  fn <- get(paste0(test, ".test"), mode = "function")
  ok <- !is.na(y)
  invisible(capture.output(cmp <- fn(y[ok], d[[treatment]][ok], err$DF, err$MS, alpha)))
  s <- cmp$summary
  means <- data.frame(Treatment = trimws(as.character(s[[1]])), Mean = s$means, N = s$N, SE = s$std.err,
                      Group = trimws(as.character(s$group)), stringsAsFactors = FALSE)
  means <- means[match(trimws(levels(d[[treatment]])), means$Treatment), ]
  means$Treatment <- levels(d[[treatment]])
  rownames(means) <- NULL
  significant <- length(p_trt) == 1 && !is.na(p_trt) && p_trt < alpha

  summary_text <- if (significant) {
    best <- means$Treatment[which.max(means$Mean)]
    sprintf("%s has a significant effect on %s (F = %.2f, p %s at alpha = %s). The highest mean is %s (%s). Means sharing a letter are not significantly different (%s test). CV = %.1f%%.",
            treatment, response, f_trt, .star_fmt_p(p_trt), alpha,
            best, format(round(max(means$Mean), 2), nsmall = 2), test, cv)
  } else {
    sprintf("No significant effect of %s on %s (F = %.2f, p %s at alpha = %s). CV = %.1f%%. Mean separation letters are not meaningful.",
            treatment, response, f_trt, .star_fmt_p(p_trt), alpha, cv)
  }

  structure(list(anova = anova, means = means, grand_mean = grand_mean, cv = cv, test = test, alpha = alpha,
                 design = design, response = response, treatment = treatment, block = block,
                 p_treatment = p_trt, significant = significant, summary = summary_text, report = report),
            class = "star_aov")
}

## Kept for the RCBD-only API used earlier.
star_aov_rcbd <- function(data, response, treatment, block,
                          test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe"), alpha = 0.05) {
  star_aov(data, response, treatment, block, design = "RCBD", test = match.arg(test), alpha = alpha)
}

print.star_aov <- function(x, ...) {
  cat(x$summary, "\n\nANOVA (", x$design, ")\n", sep = "")
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

.star_fmt_p <- function(p) if (is.na(p)) "= NA" else if (p < 1e-4) "< 0.0001" else paste("=", formatC(p, format = "f", digits = 4))
