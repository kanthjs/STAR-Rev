# Reference values come from the ORIGINAL STAR 2.0.2 (reference/original-outputs/Correlation.txt, mtcars).
test_that("Pearson correlation matches the original STAR (mtcars: mpg, hp, wt)", {
  r <- star_correlation(mtcars, c("mpg", "hp", "wt"))
  expect_s3_class(r, "star_correlation")
  expect_equal(round(r$r["mpg", "hp"], 4), -0.7762)
  expect_equal(round(r$r["mpg", "wt"], 4), -0.8677)
  expect_equal(round(r$r["hp", "wt"], 4), 0.6587)
  expect_equal(signif(r$p["mpg", "hp"], 6), 1.78784e-07)
  expect_equal(signif(r$p["hp", "wt"], 6), 4.14583e-05)
  expect_equal(r$n[1, 2], 32)
  expect_equal(nrow(r$pairs), 3)
  expect_true(all(r$pairs$Significant))
  expect_true(all(r$pairs$Lower < r$pairs$r & r$pairs$r < r$pairs$Upper))
  expect_match(r$summary, "Strongest: mpg and wt")
})

test_that("Spearman works, has no confidence interval, and p-values can be adjusted", {
  r <- star_correlation(mtcars, c("mpg", "hp", "wt", "qsec"), method = "spearman")
  expect_equal(r$symbol, "rho")
  expect_true(all(is.na(r$pairs$Lower)))
  expect_equal(round(r$r["mpg", "wt"], 4), round(cor(mtcars$mpg, mtcars$wt, method = "spearman"), 4))
  a <- star_correlation(mtcars, c("mpg", "hp", "wt", "qsec"), adjust = "bonferroni")
  expect_equal(a$pairs$PAdj, pmin(1, a$pairs$P * 6))
})

test_that("missing values are removed pair by pair and text numbers are read", {
  d <- mtcars[1:20, c("mpg", "hp", "wt")]
  d$mpg[1:3] <- NA
  d$hp <- as.character(d$hp); d$hp[5] <- "n/a"
  r <- star_correlation(d, c("mpg", "hp", "wt"))
  expect_equal(r$n["mpg", "hp"], 16)
  expect_equal(r$n["hp", "wt"], 19)
  expect_true(any(grepl("pair by pair", r$data_notes)))
})

test_that("bad input gives clear errors", {
  expect_error(star_correlation(mtcars, "mpg"), "at least two")
  expect_error(star_correlation(mtcars, c("mpg", "nope")), "not found")
  d <- mtcars; d$k <- 1
  expect_error(star_correlation(d, c("mpg", "k")), "constant")
  d$txt <- rep(c("a", "b"), 16)
  expect_error(star_correlation(d, c("mpg", "txt")), "no numbers|mostly text")
})

test_that("plots are ggplot objects", {
  r <- star_correlation(mtcars, c("mpg", "hp", "wt"))
  expect_s3_class(star_ggplot_correlation(r), "ggplot")
  expect_s3_class(star_ggplot_correlation(r, pair = c("mpg", "wt"), main = "T", xlab = "X", ylab = "Y"), "ggplot")
  expect_error(star_ggplot_correlation(r, pair = c("mpg", "zzz")), "two of")
  f <- tempfile(fileext = ".png"); star_ggsave(star_ggplot_correlation(r), f, dpi = 72)
  expect_gt(file.size(f), 1000)
})
