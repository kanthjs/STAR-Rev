## New API (not part of the original STAR): structured results for a split-plot experiment in randomized complete
## blocks. The main-plot factor is applied to whole plots, the sub-plot factor to parts of each whole plot.
##   model: Rep + A*B + Error(Rep:A)   (A = main plot, B = sub plot)
##   Error(a) tests Rep and A; Error(b) tests B and A:B.
## Mean separation follows STAR: with a significant interaction, B is compared within each level of A (Error(b)) and
## A within each level of B (pooled error of (a) and (b), Satterthwaite df); otherwise the significant main effects
## are compared (A with Error(a), B with Error(b)). The design must be complete and balanced (one observation per
## block x main x sub combination).

star_splitplot <- function(data, response, main, sub, block,
                           test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe", "bonferroni"), alpha = 0.05) {
  test <- match.arg(test)
  if (!is.data.frame(data)) stop("'data' must be a data frame.")
  if (length(main) != 1 || length(sub) != 1) stop("A split-plot analysis needs exactly one main-plot factor and one sub-plot factor.")
  if (is.null(block) || !nzchar(block)) stop("A split-plot analysis needs a block (replication) column.")
  cols <- c(response, main, sub, block)
  for (v in cols) if (!v %in% names(data)) stop("Column '", v, "' not found.")
  if (length(unique(cols)) < length(cols)) stop("Response, main-plot factor, sub-plot factor and block must be different columns.")
  factors <- c(main, sub)
  d <- star_prepare_data(data, response, factors, block)
  data_notes <- attr(d, "notes")
  if (any(is.na(d[[response]])))
    stop("The response has ", sum(is.na(d[[response]])), " missing value(s); the split-plot analysis needs a complete, balanced layout. Remove or fill them in and try again.", call. = FALSE)
  cnt <- table(d[[block]], d[[main]], d[[sub]])
  if (any(cnt != 1))
    stop("The layout is not complete and balanced: each block x ", main, " x ", sub, " combination must appear exactly once.", call. = FALSE)

  # whole-plot unit = block x main-plot level
  d$.wp <- interaction(d[[block]], d[[main]], drop = TRUE)
  fm <- stats::as.formula(sprintf("`%s` ~ `%s` + `%s` * `%s` + Error(.wp)", response, block, main, sub))
  fit <- stats::aov(fm, data = d)
  st <- summary(fit)
  sa <- st[[1]][[1]]; sb <- st[[2]][[1]]
  rn <- function(s) trimws(rownames(s))
  if (nrow(sa) != 3 || nrow(sb) != 3) stop("STAR could not fit this split-plot model.", call. = FALSE)
  inter_name <- paste(main, sub, sep = ":")
  rows <- function(s, nm) data.frame(Source = nm, DF = s[["Df"]], SS = s[["Sum Sq"]], MS = s[["Mean Sq"]],
                                     F = s[["F value"]], P = s[["Pr(>F)"]], stringsAsFactors = FALSE)
  a_tab <- rows(sa, c(block, main, "Error(a)"))
  b_tab <- rows(sb, c(sub, inter_name, "Error(b)"))
  anova <- rbind(a_tab, b_tab)
  anova <- rbind(anova, data.frame(Source = "Total", DF = sum(anova$DF), SS = sum(anova$SS), MS = NA_real_,
                                   F = NA_real_, P = NA_real_, stringsAsFactors = FALSE))
  rownames(anova) <- NULL
  err_a <- a_tab[3, ]; err_b <- b_tab[3, ]

  effects <- anova[match(c(main, sub, inter_name), anova$Source), c("Source", "DF", "F", "P")]
  names(effects)[1] <- "Effect"
  effects$Significant <- !is.na(effects$P) & effects$P < alpha
  den <- list(err_a, err_b, err_b)
  effects$ErrDF <- vapply(den, function(e) as.numeric(e$DF), 0)
  ss <- anova$SS[match(effects$Effect, anova$Source)]
  effects$PartialEta2 <- ss / (ss + vapply(den, function(e) e$SS, 0))
  rownames(effects) <- NULL

  y <- d[[response]]
  grand_mean <- mean(y)
  cv_a <- 100 * sqrt(err_a$MS) / grand_mean
  cv_b <- 100 * sqrt(err_b$MS) / grand_mean
  nb <- nlevels(d[[sub]])
  pooled_ms <- (err_a$MS + (nb - 1) * err_b$MS) / nb
  pooled_df <- floor((err_a$MS + (nb - 1) * err_b$MS)^2 / (err_a$MS^2 / err_a$DF + ((nb - 1) * err_b$MS)^2 / err_b$DF))

  test_for <- function(f) if (test == "auto") (if (nlevels(d[[f]]) <= 5) "LSD" else "HSD") else test
  compare <- function(yy, tr, tst, dfe, mse) {
    invisible(capture.output(cmp <- if (tst == "bonferroni") LSD.test(yy, tr, dfe, mse, alpha, p.adj = "bonferroni")
                                    else get(paste0(tst, ".test"), mode = "function")(yy, tr, dfe, mse, alpha)))
    s <- cmp$summary
    out <- data.frame(Treatment = trimws(as.character(s[[1]])), Mean = s$means, N = s$N, SE = s$std.err,
                      Group = trimws(as.character(s$group)), stringsAsFactors = FALSE)
    out <- out[match(trimws(levels(tr)), out$Treatment), ]
    out$Treatment <- levels(tr)
    out$SE <- sqrt(mse / out$N)             # model-based standard error of a mean
    rownames(out) <- NULL
    out
  }

  interaction_sig <- isTRUE(effects$Significant[3])
  tests_used <- character()
  means <- list()
  for (f in factors) {
    tst <- test_for(f)
    e <- if (f == main) err_a else err_b
    m <- compare(y, d[[f]], tst, e$DF, e$MS)
    if (interaction_sig) m$Group <- NA_character_
    means[[f]] <- m
    tests_used[f] <- tst
  }

  cells <- do.call(rbind, lapply(levels(d[[main]]), function(a) do.call(rbind, lapply(levels(d[[sub]]), function(b) {
    v <- y[d[[main]] == a & d[[sub]] == b]
    data.frame(a, b, Mean = mean(v), N = length(v), SE = sd(v) / sqrt(length(v)), stringsAsFactors = FALSE)
  }))))
  names(cells)[1:2] <- c(main, sub)
  rownames(cells) <- NULL

  simple <- NULL
  if (interaction_sig) {
    simple <- list()
    # compare pair[1] at each level of pair[2]: sub within main uses Error(b); main within sub uses the pooled error
    for (pair in list(c(main, sub), c(sub, main))) {
      cmp_f <- pair[1]; at_f <- pair[2]; tst <- test_for(cmp_f)
      dfe <- if (cmp_f == sub) err_b$DF else pooled_df
      mse <- if (cmp_f == sub) err_b$MS else pooled_ms
      parts <- lapply(levels(d[[at_f]]), function(lv) {
        idx <- d[[at_f]] == lv
        m <- compare(y[idx], droplevels(d[[cmp_f]][idx]), tst, dfe, mse)
        out <- data.frame(lv, m, stringsAsFactors = FALSE)
        names(out)[1:2] <- c(at_f, cmp_f)
        out
      })
      sm <- do.call(rbind, parts); rownames(sm) <- NULL
      attr(sm, "compare") <- cmp_f; attr(sm, "at") <- at_f; attr(sm, "test") <- tst
      simple[[paste(cmp_f, "within", at_f)]] <- sm
    }
  }

  # classic STAR printout of the same analysis (optional; the structured results above do not depend on it)
  report <- tryCatch({
    args <- list(data = d[, c(response, main, sub, block)], design = "SplitRCBD", respvar = response,
                 factor1 = main, factor2 = sub, rep1 = block, sig = alpha)
    if (!test %in% c("auto", "bonferroni")) { args$pwTest <- test; args$pwVar <- response }
    capture.output(do.call(AOVTest, args))
  }, error = function(e) "The classic STAR report could not be produced for this data.")

  summary_text <- .star_split_summary(anova, effects, means, factors, response, interaction_sig, tests_used, alpha, cv_a, cv_b)
  apa <- .star_apa(effects, err_b, response)

  structure(list(anova = anova, effects = effects, means = means, cells = cells, simple = simple,
                 grand_mean = grand_mean, cv = cv_b, cv_a = cv_a, cv_b = cv_b, test = test, tests_used = tests_used,
                 alpha = alpha, design = "SplitRCBD", response = response, treatment = main, factors = factors,
                 main = main, sub = sub, block = block, p_treatment = effects$P[1], significant = effects$Significant[1],
                 interaction_significant = interaction_sig, summary = summary_text, apa = apa, report = report,
                 data_notes = data_notes, diagnostics = NULL),
            class = "star_aov")
}

.star_split_summary <- function(anova, effects, means, factors, response, interaction_sig, tests_used, alpha, cv_a, cv_b) {
  stat <- function(nm) { i <- anova$Source == nm; sprintf("F = %.2f, p %s", anova$F[i], .star_fmt_p(anova$P[i])) }
  best <- function(f) { m <- means[[f]]; sprintf("%s (%s)", m$Treatment[which.max(m$Mean)], format(round(max(m$Mean), 2), nsmall = 2)) }
  cv_txt <- sprintf("CV(a) = %.1f%% (main plots), CV(b) = %.1f%% (sub plots).", cv_a, cv_b)
  inter <- paste(factors, collapse = ":")
  parts <- if (interaction_sig) {
    sprintf("The %s x %s interaction is significant (%s), so the effect of each factor depends on the level of the other; compare means within levels (simple effects) rather than overall means.",
            factors[1], factors[2], stat(inter))
  } else {
    c(sprintf("The %s x %s interaction is not significant (%s).", factors[1], factors[2], stat(inter)),
      vapply(factors, function(f) {
        if (effects$Significant[effects$Effect == f])
          sprintf("%s (%s plot) has a significant main effect (%s); the highest mean is %s (%s test).", f,
                  if (f == factors[1]) "main" else "sub", stat(f), best(f), tests_used[[f]])
        else sprintf("%s (%s plot) has no significant main effect (%s).", f, if (f == factors[1]) "main" else "sub", stat(f))
      }, ""))
  }
  paste(c(parts, cv_txt), collapse = " ")
}
