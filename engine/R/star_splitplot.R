## New API (not part of the original STAR): structured results for a split-plot experiment in randomized complete
## blocks. The main-plot factor is applied to whole plots, the sub-plot factor to parts of each whole plot.
##   model: Rep + A*B + Error(Rep:A)   (A = main plot, B = sub plot)
##   Error(a) tests Rep and A; Error(b) tests B and A:B.
## Mean separation follows STAR: with a significant interaction, B is compared within each level of A (Error(b)) and
## A within each level of B (pooled error of (a) and (b), Satterthwaite df); otherwise the significant main effects
## are compared (A with Error(a), B with Error(b)). The design must be complete and balanced (one observation per
## block x main x sub combination).

star_splitplot <- function(data, response, main, sub, block,
                           test = c("auto", "LSD", "HSD", "duncan", "SNK", "scheffe", "bonferroni"), alpha = 0.05, report = TRUE) {
  test <- match.arg(test)
  if (!is.data.frame(data)) stop("'data' must be a data frame.")
  if (length(main) != 1 || length(sub) != 1) stop("A split-plot analysis needs exactly one main-plot factor and one sub-plot factor.")
  if (is.null(block) || !nzchar(block)) stop("A split-plot analysis needs a block (replication) column.")
  cols <- c(response, main, sub, block)
  .star_check_columns(data, cols, "Response, main-plot factor, sub-plot factor and block")
  factors <- c(main, sub)
  d <- star_prepare_data(data, response, factors, block)
  data_notes <- attr(d, "notes")
  if (any(is.na(d[[response]]))) {
    stop("The response has ", sum(is.na(d[[response]])), " missing value(s); the split-plot analysis needs a complete, balanced layout. Remove or fill them in and try again.", call. = FALSE)
  }
  cnt <- table(d[[block]], d[[main]], d[[sub]])
  if (any(cnt != 1)) {
    stop("The layout is not complete and balanced: each block x ", main, " x ", sub, " combination must appear exactly once.", call. = FALSE)
  }

  # whole-plot unit = block x main-plot level
  d$.wp <- interaction(d[[block]], d[[main]], drop = TRUE)
  fm <- stats::as.formula(sprintf("`%s` ~ `%s` + `%s` * `%s` + Error(.wp)", response, block, main, sub))
  fit <- stats::aov(fm, data = d)
  st <- summary(fit)
  sa <- st[[1]][[1]]
  sb <- st[[2]][[1]]
  rn <- \(s) trimws(rownames(s))
  if (nrow(sa) != 3 || nrow(sb) != 3) stop("STAR could not fit this split-plot model.", call. = FALSE)
  inter_name <- paste(main, sub, sep = ":")
  rows <- function(s, nm) {
    data.frame(
      Source = nm, DF = s[["Df"]], SS = s[["Sum Sq"]], MS = s[["Mean Sq"]],
      F = s[["F value"]], P = s[["Pr(>F)"]], stringsAsFactors = FALSE
    )
  }
  a_tab <- rows(sa, c(block, main, "Error(a)"))
  b_tab <- rows(sb, c(sub, inter_name, "Error(b)"))
  anova <- rbind(a_tab, b_tab)
  anova <- rbind(anova, data.frame(
    Source = "Total", DF = sum(anova$DF), SS = sum(anova$SS), MS = NA_real_,
    F = NA_real_, P = NA_real_, stringsAsFactors = FALSE
  ))
  rownames(anova) <- NULL
  err_a <- a_tab[3, ]
  err_b <- b_tab[3, ]

  effects <- anova[match(c(main, sub, inter_name), anova$Source), c("Source", "DF", "F", "P")]
  names(effects)[1] <- "Effect"
  effects$Significant <- !is.na(effects$P) & effects$P < alpha
  den <- list(err_a, err_b, err_b)
  effects$ErrDF <- vapply(den, \(e) as.numeric(e$DF), 0)
  ss <- anova$SS[match(effects$Effect, anova$Source)]
  effects$PartialEta2 <- ss / (ss + vapply(den, \(e) e$SS, 0))
  rownames(effects) <- NULL

  y <- d[[response]]
  grand_mean <- mean(y)
  cv_a <- 100 * sqrt(err_a$MS) / grand_mean
  cv_b <- 100 * sqrt(err_b$MS) / grand_mean
  nb <- nlevels(d[[sub]])
  pooled_ms <- (err_a$MS + (nb - 1) * err_b$MS) / nb
  pooled_df <- floor((err_a$MS + (nb - 1) * err_b$MS)^2 / (err_a$MS^2 / err_a$DF + ((nb - 1) * err_b$MS)^2 / err_b$DF))

  interaction_sig <- isTRUE(effects$Significant[3])
  means_err <- setNames(list(err_a, err_b), factors)
  mm <- .star_main_means(
    y, d, factors, test, lapply(means_err, `[[`, "DF"), lapply(means_err, `[[`, "MS"),
    alpha, interaction_sig,
    model_se = TRUE
  )
  means <- mm$means
  tests_used <- mm$tests
  cells <- .star_cell_means(y, d, main, sub)

  # sub within main uses Error(b); main within sub uses the pooled error
  simple <- if (interaction_sig) {
    .star_simple_effects(
      y, d, factors, test,
      setNames(list(pooled_df, err_b$DF), factors), setNames(list(pooled_ms, err_b$MS), factors),
      alpha,
      model_se = TRUE
    )
  }

  # classic STAR printout of the same analysis; with `report = FALSE` it is left to star_report()
  d <- d[, c(response, main, sub, block)]
  report <- if (report) .star_split_report(d, response, main, sub, block, test, alpha)

  summary_text <- .star_split_summary(anova, effects, means, factors, response, interaction_sig, tests_used, alpha, cv_a, cv_b)
  apa <- .star_apa(effects, err_b, response)

  structure(
    list(
      anova = anova, effects = effects, means = means, cells = cells, simple = simple,
      grand_mean = grand_mean, cv = cv_b, cv_a = cv_a, cv_b = cv_b, test = test, tests_used = tests_used,
      alpha = alpha, design = "SplitRCBD", response = response, treatment = main, factors = factors,
      main = main, sub = sub, block = block, p_treatment = effects$P[1], significant = effects$Significant[1],
      interaction_significant = interaction_sig, summary = summary_text, apa = apa, report = report,
      data_notes = data_notes, diagnostics = NULL, data = d
    ),
    class = "star_aov"
  )
}

.star_split_report <- function(d, response, main, sub, block, test, alpha) {
  args <- list(
    data = d, design = "SplitRCBD", respvar = response,
    factor1 = main, factor2 = sub, rep1 = block, sig = alpha
  )
  if (!test %in% c("auto", "bonferroni")) {
    args$pwTest <- test
    args$pwVar <- response
  }
  tryCatch(
    capture.output(do.call(AOVTest, args)),
    error = \(e) "The classic STAR report could not be produced for this data."
  )
}

.star_split_summary <- function(anova, effects, means, factors, response, interaction_sig, tests_used, alpha, cv_a, cv_b) {
  stat <- \(nm) .star_stat(anova, nm)
  best <- \(f) .star_best(means[[f]])
  cv_txt <- sprintf("CV(a) = %.1f%% (main plots), CV(b) = %.1f%% (sub plots).", cv_a, cv_b)
  inter <- paste(factors, collapse = ":")
  parts <- if (interaction_sig) {
    sprintf(
      "The %s x %s interaction is significant (%s), so the effect of each factor depends on the level of the other; compare means within levels (simple effects) rather than overall means.",
      factors[1], factors[2], stat(inter)
    )
  } else {
    c(
      sprintf("The %s x %s interaction is not significant (%s).", factors[1], factors[2], stat(inter)),
      vapply(factors, function(f) {
        if (effects$Significant[effects$Effect == f]) {
          sprintf(
            "%s (%s plot) has a significant main effect (%s); the highest mean is %s (%s test).", f,
            if (f == factors[1]) "main" else "sub", stat(f), best(f), tests_used[[f]]
          )
        } else {
          sprintf("%s (%s plot) has no significant main effect (%s).", f, if (f == factors[1]) "main" else "sub", stat(f))
        }
      }, "")
    )
  }
  paste(c(parts, cv_txt), collapse = " ")
}
