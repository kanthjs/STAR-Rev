## New API (not part of the original STAR): structured results for a one- or two-factor analysis of variance.
## The original functions print text; these wrap them and return data frames so that an app can
## render tables, draw plots and export to Excel.
##   design = "CRD"  : completely randomized design (no blocks)
##   design = "RCBD" : randomized complete block design (`block` required)
##   treatment       : one factor, or two factors (factorial layout with interaction)
## Mean separation follows STAR: with a significant interaction the simple effects are compared (each factor at
## each level of the other); otherwise the significant main effects are compared.

star_aov <- function(data, response, treatment, block = NULL, design = c("CRD", "RCBD"),
                     test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe"), alpha = 0.05) {
  design <- match.arg(design)
  test <- match.arg(test)
  factors <- treatment
  if (!is.data.frame(data)) stop("'data' must be a data frame.")
  if (length(factors) < 1) stop("Choose at least one treatment factor.")
  if (length(factors) > 2) stop("Only one or two treatment factors are supported so far.")
  if (design == "RCBD" && (is.null(block) || !nzchar(block))) stop("An RCBD analysis needs a block column.")
  if (design == "CRD") block <- NULL
  cols <- c(response, factors, block)
  for (v in cols) if (!v %in% names(data)) stop("Column '", v, "' not found.")
  if (length(unique(cols)) < length(cols)) stop("Response, treatment factors and block must be different columns.")
  d <- star_prepare_data(data, response, factors, block)     # coerce / clean the chosen columns, drop unusable rows
  data_notes <- attr(d, "notes")
  counts <- table(d[, factors, drop = FALSE])
  if (any(counts == 0)) stop("Some treatment combinations have no observations; the design must be complete.")

  # Let STAR choose the test per effect (LSD if <= 5 levels, else Tukey) unless the user picked one
  args <- list(data = d, design = design, respvar = response, factor1 = factors, sig = alpha)
  if (design == "RCBD") args$rep1 <- block
  if (test != "auto") { args$pwTest <- test; args$pwVar <- response }
  report <- capture.output(res <- do.call(AOVTest, args))
  err_line <- grep("^ERROR:", report, value = TRUE)
  if (length(err_line) || is.null(res$aovTable) || length(res$aovTable) == 0)
    stop(if (length(err_line)) sub("^ERROR:[[:space:]]*", "", err_line[1]) else "STAR could not fit this model",
         if (any(is.na(d[[response]]))) paste0(" The response has ", sum(is.na(d[[response]])), " missing value(s); remove or fill them in and try again.") else "",
         call. = FALSE)
  if (any(grepl("estimates of the missing values", report))) data_notes <- c(data_notes, "Missing response values were estimated by STAR's missing-data procedure (RCBD); degrees of freedom are adjusted.")
  raw <- ConstructAOVTable(res$aovTable[[1]][[1]])
  anova <- data.frame(Source = trimws(rownames(raw)), DF = raw[[1]], SS = raw[[2]], MS = raw[[3]],
                      F = raw[[4]], P = raw[[5]], row.names = NULL, stringsAsFactors = FALSE)
  err <- anova[anova$Source == "Error", ]
  inter_name <- if (length(factors) == 2) paste(factors, collapse = ":") else NULL
  effect_names <- c(factors, inter_name)
  effects <- anova[match(effect_names, anova$Source), c("Source", "DF", "F", "P")]
  names(effects)[1] <- "Effect"
  effects$Significant <- !is.na(effects$P) & effects$P < alpha
  rownames(effects) <- NULL

  y <- d[[response]]
  ok <- !is.na(y)
  grand_mean <- mean(y, na.rm = TRUE)
  cv <- 100 * sqrt(err$MS) / grand_mean

  test_for <- function(f) if (test == "auto") (if (nlevels(d[[f]]) <= 5) "LSD" else "HSD") else test
  compare <- function(yy, tr, tst) {   # one STAR mean-separation test on a set of observations
    invisible(capture.output(cmp <- get(paste0(tst, ".test"), mode = "function")(yy, tr, err$DF, err$MS, alpha)))
    s <- cmp$summary
    out <- data.frame(Treatment = trimws(as.character(s[[1]])), Mean = s$means, N = s$N, SE = s$std.err,
                      Group = trimws(as.character(s$group)), stringsAsFactors = FALSE)
    out <- out[match(trimws(levels(tr)), out$Treatment), ]
    out$Treatment <- levels(tr)
    rownames(out) <- NULL
    out
  }

  interaction_sig <- !is.null(inter_name) && isTRUE(effects$Significant[effects$Effect == inter_name])
  tests_used <- character()
  means <- list()
  for (f in factors) {
    tst <- test_for(f)
    m <- compare(y[ok], d[[f]][ok], tst)
    if (interaction_sig) m$Group <- NA_character_      # main effects are not compared when the interaction is significant
    means[[f]] <- m
    tests_used[f] <- tst
  }

  cells <- simple <- NULL
  if (length(factors) == 2) {
    f1 <- factors[1]; f2 <- factors[2]
    cells <- do.call(rbind, lapply(levels(d[[f1]]), function(a) do.call(rbind, lapply(levels(d[[f2]]), function(b) {
      v <- y[d[[f1]] == a & d[[f2]] == b & ok]
      data.frame(a, b, Mean = mean(v), N = length(v), SE = sd(v) / sqrt(length(v)), stringsAsFactors = FALSE)
    }))))
    names(cells)[1:2] <- c(f1, f2)
    rownames(cells) <- NULL
    if (interaction_sig) {
      simple <- list()
      for (pair in list(c(f1, f2), c(f2, f1))) {            # compare pair[1] at each level of pair[2]
        cmp_f <- pair[1]; at_f <- pair[2]; tst <- test_for(cmp_f)
        parts <- lapply(levels(d[[at_f]]), function(lv) {
          idx <- d[[at_f]] == lv & ok
          m <- compare(y[idx], droplevels(d[[cmp_f]][idx]), tst)
          out <- data.frame(lv, m, stringsAsFactors = FALSE)
          names(out)[1:2] <- c(at_f, cmp_f)
          out
        })
        sm <- do.call(rbind, parts); rownames(sm) <- NULL
        attr(sm, "compare") <- cmp_f; attr(sm, "at") <- at_f; attr(sm, "test") <- tst
        simple[[paste(cmp_f, "within", at_f)]] <- sm
      }
    }
  }

  diagnostics <- tryCatch(star_diagnostics(data, response, factors, block, alpha), error = function(e) NULL)

  summary_text <- .star_aov_summary(anova, effects, means, factors, response, interaction_sig, tests_used, alpha, cv)

  structure(list(anova = anova, effects = effects, means = means, cells = cells, simple = simple,
                 grand_mean = grand_mean, cv = cv, test = test, tests_used = tests_used,
                 alpha = alpha, design = design, response = response, treatment = factors[1], factors = factors,
                 block = block, p_treatment = effects$P[1], significant = effects$Significant[1],
                 interaction_significant = interaction_sig, summary = summary_text, report = report,
                 data_notes = data_notes, diagnostics = diagnostics),
            class = "star_aov")
}

.star_aov_summary <- function(anova, effects, means, factors, response, interaction_sig, tests_used, alpha, cv) {
  f_of <- function(nm) anova$F[anova$Source == nm]
  p_of <- function(nm) anova$P[anova$Source == nm]
  stat <- function(nm) sprintf("F = %.2f, p %s", f_of(nm), .star_fmt_p(p_of(nm)))
  best <- function(f) { m <- means[[f]]; sprintf("%s (%s)", m$Treatment[which.max(m$Mean)], format(round(max(m$Mean), 2), nsmall = 2)) }
  cv_txt <- sprintf("CV = %.1f%%.", cv)
  if (length(factors) == 1) {
    f <- factors
    if (effects$Significant[1]) {
      sprintf("%s has a significant effect on %s (%s at alpha = %s). The highest mean is %s. Means sharing a letter are not significantly different (%s test). %s",
              f, response, stat(f), alpha, best(f), tests_used[[f]], cv_txt)
    } else {
      sprintf("No significant effect of %s on %s (%s at alpha = %s). %s Mean separation letters are not meaningful.",
              f, response, stat(f), alpha, cv_txt)
    }
  } else {
    inter <- paste(factors, collapse = ":")
    parts <- character()
    if (interaction_sig) {
      parts <- c(parts, sprintf("The %s x %s interaction is significant (%s), so the effect of each factor depends on the level of the other; compare means within levels (simple effects) rather than overall means.",
                                factors[1], factors[2], stat(inter)))
    } else {
      parts <- c(parts, sprintf("The %s x %s interaction is not significant (%s).", factors[1], factors[2], stat(inter)))
      for (f in factors) {
        parts <- c(parts, if (effects$Significant[effects$Effect == f]) {
          sprintf("%s has a significant main effect (%s); the highest mean is %s (%s test).", f, stat(f), best(f), tests_used[[f]])
        } else sprintf("%s has no significant main effect (%s).", f, stat(f)))
      }
    }
    paste(c(parts, cv_txt), collapse = " ")
  }
}

## Kept for the RCBD-only API used earlier.
star_aov_rcbd <- function(data, response, treatment, block,
                          test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe"), alpha = 0.05) {
  star_aov(data, response, treatment, block, design = "RCBD", test = match.arg(test), alpha = alpha)
}

print.star_aov <- function(x, ...) {
  cat(x$summary, "\n\nANOVA (", x$design, ")\n", sep = "")
  print(x$anova, row.names = FALSE, digits = 4)
  for (nm in names(x$means)) { cat("\nMeans of ", nm, "\n", sep = ""); print(x$means[[nm]], row.names = FALSE, digits = 4) }
  if (!is.null(x$cells)) { cat("\nCell means\n"); print(x$cells, row.names = FALSE, digits = 4) }
  for (nm in names(x$simple)) { cat("\nSimple effects: ", nm, "\n", sep = ""); print(x$simple[[nm]], row.names = FALSE, digits = 4) }
  invisible(x)
}

## Bar plots with +/- 1 SE bars and mean-separation letters.
##   effect = NULL / a factor name : bars for the means of that factor
##   effect = "interaction"        : grouped bars (two-factor results); groups = the second factor,
##                                   bars = the first factor, letters = first factor compared within each group
star_plot_means <- function(x, effect = NULL, main = NULL, col = NULL) {
  op <- par(mar = c(5, 5, 3, 1)); on.exit(par(op))
  if (identical(effect, "interaction") && !is.null(x$cells)) {
    f1 <- x$factors[1]; f2 <- x$factors[2]
    cl <- x$cells
    mat <- tapply(cl$Mean, list(cl[[f1]], cl[[f2]]), sum)
    se <- tapply(cl$SE, list(cl[[f1]], cl[[f2]]), sum)
    pal <- if (is.null(col)) c("#4C78A8", "#F58518", "#54A24B", "#E45756", "#72B7B2", "#B279A2", "#EECA3B", "#9D755D")[seq_len(nrow(mat))] else col
    top <- max(mat + se, na.rm = TRUE)
    mid <- barplot(mat, beside = TRUE, col = pal, border = NA, ylim = c(0, top * 1.18),
                   xlab = f2, ylab = paste("Mean", x$response),
                   main = if (is.null(main)) paste("Mean", x$response, "by", f1, "and", f2) else main,
                   legend.text = rownames(mat), args.legend = list(title = f1, x = "topright", bty = "n", cex = 0.8))
    arrows(mid, mat - se, mid, mat + se, angle = 90, code = 3, length = 0.04)
    sm <- x$simple[[paste(f1, "within", f2)]]
    if (!is.null(sm)) {
      for (j in seq_len(ncol(mat))) for (i in seq_len(nrow(mat))) {
        g <- sm$Group[sm[[f2]] == colnames(mat)[j] & sm[[f1]] == rownames(mat)[i]]
        text(mid[i, j], mat[i, j] + se[i, j], labels = g, pos = 3, cex = 0.9)
      }
    }
    mtext(if (is.null(sm)) "Bars: mean +/- SE. Interaction not significant: no letters" else
            sprintf("Bars: mean +/- SE. Letters compare %s within each level of %s (%s test, alpha = %s)", f1, f2, attr(sm, "test"), x$alpha),
          side = 3, line = 0, cex = 0.8, col = "grey40")
    return(invisible(mid))
  }
  f <- if (is.null(effect)) x$factors[1] else effect
  m <- x$means[[f]]
  if (is.null(m)) stop("Unknown effect '", f, "'.")
  top <- max(m$Mean + m$SE, na.rm = TRUE)
  has_letters <- !all(is.na(m$Group)) && isTRUE(x$effects$Significant[x$effects$Effect == f])
  mid <- barplot(m$Mean, names.arg = m$Treatment, col = if (is.null(col)) "#4C78A8" else col, border = NA, ylim = c(0, top * 1.15),
                 xlab = f, ylab = paste("Mean", x$response),
                 main = if (is.null(main)) paste("Mean", x$response, "by", f) else main)
  arrows(mid, m$Mean - m$SE, mid, m$Mean + m$SE, angle = 90, code = 3, length = 0.05)
  if (has_letters) text(mid, m$Mean + m$SE, labels = m$Group, pos = 3, cex = 1.1)
  note <- if (has_letters) paste("Letters:", x$tests_used[[f]], "test, alpha =", x$alpha)
          else if (isTRUE(x$interaction_significant)) "Interaction significant: compare means within levels (see interaction plot)"
          else "Effect not significant"
  mtext(sprintf("Bars: mean +/- SE. %s", note), side = 3, line = 0, cex = 0.8, col = "grey40")
  invisible(mid)
}

.star_fmt_p <- function(p) if (is.na(p)) "= NA" else if (p < 1e-4) "< 0.0001" else paste("=", formatC(p, format = "f", digits = 4))
