# Structured API on top of AOVTest. Reference values: original STAR output for the rice example
data(GomezSplitRCBD, package = "STAR", envir = environment())
d <- subset(GomezSplitRCBD, Variety == "IR5")[, c("Rep", "Nitrogen", "Yield")]

test_that("star_aov_rcbd returns tables that match the printed STAR report", {
  r <- star_aov_rcbd(d, "Yield", "Nitrogen", "Rep", test = "LSD")
  expect_s3_class(r, "star_aov_rcbd")
  expect_equal(r$anova$Source, c("Rep", "Nitrogen", "Error", "Total"))
  expect_equal(r$anova$DF, c(2, 5, 10, 17))
  expect_equal(round(r$anova$F[2], 2), 8.54)
  expect_equal(round(r$p_treatment, 4), 0.0022)
  expect_equal(round(r$cv, 2), 9.42)
  expect_equal(round(r$grand_mean, 1), 6155.5)
  expect_equal(nrow(r$means), 6)
  expect_equal(r$means$Treatment, levels(factor(d$Nitrogen)))
  expect_equal(r$means$Group, c("b", "a", "a", "a", "a", "a"))
  expect_true(r$significant)
  expect_match(r$summary, "significant effect")
  expect_true(any(grepl("ANOVA TABLE", r$report)))
})

test_that("every mean-comparison choice works and 'auto' follows STAR's rule", {
  for (tst in c("LSD", "HSD", "duncan", "SNK", "scheffe")) {
    r <- star_aov_rcbd(d, "Yield", "Nitrogen", "Rep", test = tst)
    expect_equal(r$test, tst)
    expect_equal(nrow(r$means), 6)
  }
  expect_equal(star_aov_rcbd(d, "Yield", "Nitrogen", "Rep")$test, "HSD")  # 6 levels > 5
})

test_that("bad input gives clear errors", {
  expect_error(star_aov_rcbd(d, "Nope", "Nitrogen", "Rep"), "not found")
  expect_error(star_aov_rcbd(d, "Yield", "Yield", "Rep"), "different columns")
  expect_error(star_aov_rcbd(d, "Nitrogen", "Rep", "Yield"), "numeric")
})

test_that("plot runs without error", {
  r <- star_aov_rcbd(d, "Yield", "Nitrogen", "Rep", test = "LSD")
  f <- tempfile(fileext = ".png"); png(f); on.exit(unlink(f))
  expect_silent(star_plot_means(r)); dev.off()
  expect_true(file.exists(f))
})
