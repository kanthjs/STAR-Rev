# Reference values come from the ORIGINAL STAR 2.0.2 (reference/original-outputs/Regression.txt, mpg ~ wt + hp).
# The original prints CV(%) = 30.00, which is the CV of mpg itself (sd / mean); star_regression() reports the
# CV of the model (root MSE / mean = 12.91).
test_that("multiple regression matches the original STAR (mtcars: mpg ~ wt + hp)", {
  r <- star_regression(mtcars, "mpg", c("wt", "hp"))
  expect_s3_class(r, "star_regression")
  expect_equal(r$anova$Source, c("Model", "Error", "Total"))
  expect_equal(r$anova$DF, c(2, 29, 31))
  expect_equal(round(r$anova$SS[1:2], 4), c(930.9994, 195.0478))
  expect_equal(round(r$anova$F[1], 2), 69.21)
  expect_equal(round(c(r$fit$rmse, r$fit$mean_y), 2), c(2.59, 20.09))
  expect_equal(round(c(r$fit$r2, r$fit$adj_r2), 4), c(0.8268, 0.8148))
  expect_equal(round(r$fit$cv, 2), 12.91)
  cf <- r$coefficients
  expect_equal(cf$Term, c("(Intercept)", "wt", "hp"))
  expect_equal(round(cf$Estimate, 2), c(37.23, -3.88, -0.03))
  expect_equal(round(cf$SE, 2), c(1.60, 0.63, 0.01))
  expect_equal(round(cf$t, 2), c(23.28, -6.13, -3.52))
  expect_equal(round(cf$Lower, 2), c(33.96, -5.17, -0.05))
  expect_equal(round(cf$Upper, 2), c(40.50, -2.58, -0.01))
  expect_equal(round(cf$P[3], 4), 0.0015)
  expect_equal(round(cf$VIF[2:3], 2), c(1.77, 1.77))
  expect_true(is.finite(r$diagnostics$homogeneity$p))
  expect_s3_class(r$diagnostics, "star_diagnostics")
})

test_that("simple regression agrees with lm and has standardized beta = r", {
  r <- star_regression(mtcars, "mpg", "wt")
  fit <- lm(mpg ~ wt, mtcars)
  expect_equal(unname(r$coefficients$Estimate), unname(coef(fit)))
  expect_equal(r$coefficients$StdBeta[2], cor(mtcars$mpg, mtcars$wt))
  expect_equal(r$fit$r2, summary(fit)$r.squared)
  expect_true(all(is.na(r$coefficients$VIF)))
  expect_equal(r$equation, "mpg = 37.29 - 5.344 wt")
})

test_that("quadratic regression reports the optimum and predicts with a warning flag outside the range", {
  sr <- data.frame(rate = as.numeric(as.character(STAR::SeedingRate$SeedingRate)), y = STAR::SeedingRate$GrainYield)
  r <- star_regression(sr, "y", "rate", degree = 2)
  fit <- lm(y ~ rate + I(rate^2), sr)
  expect_equal(unname(r$coefficients$Estimate), unname(coef(fit)))
  b <- coef(fit)
  expect_equal(r$optimum$x, unname(-b[2] / (2 * b[3])))
  expect_equal(r$optimum$type, "maximum")
  expect_true(r$optimum$inside)
  expect_match(r$summary, "maximum at rate")
  pr <- star_predict(r, data.frame(rate = c(50, 500)))
  expect_equal(pr$Predicted[1], unname(predict(fit, data.frame(rate = 50))))
  expect_equal(pr$Extrapolated, c(FALSE, TRUE))
})

test_that("missing rows are dropped with a note and bad models are refused", {
  d <- mtcars; d$wt[2] <- NA
  r <- star_regression(d, "mpg", c("wt", "hp"))
  expect_equal(r$fit$n, 31)
  expect_true(any(grepl("left out", r$data_notes)))
  expect_error(star_regression(mtcars, "mpg", character()), "at least one predictor")
  expect_error(star_regression(mtcars, "mpg", c("wt", "hp"), degree = 2), "exactly one predictor")
  d2 <- mtcars; d2$wt2 <- 2 * d2$wt
  expect_error(star_regression(d2, "mpg", c("wt", "wt2")), "collinear")
  expect_error(star_regression(mtcars[1:3, ], "mpg", c("wt", "hp")), "Too few")
  d3 <- mtcars; d3$k <- 5
  expect_error(star_regression(d3, "mpg", "k"), "constant")
})

test_that("regression plots are ggplot objects", {
  expect_s3_class(star_ggplot_regression(star_regression(mtcars, "mpg", "wt")), "ggplot")
  expect_s3_class(star_ggplot_regression(star_regression(mtcars, "mpg", c("wt", "hp")), main = "T"), "ggplot")
  sr <- data.frame(rate = as.numeric(as.character(STAR::SeedingRate$SeedingRate)), y = STAR::SeedingRate$GrainYield)
  q <- star_regression(sr, "y", "rate", degree = 2)
  expect_s3_class(star_ggplot_regression(q), "ggplot")
  expect_s3_class(star_ggplot_diagnostics(q), "ggplot")
  f <- tempfile(fileext = ".png"); star_ggsave(star_ggplot_regression(q), f, dpi = 72); expect_gt(file.size(f), 1000)
})
