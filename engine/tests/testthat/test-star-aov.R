# Structured API on top of AOVTest. Reference values come from the ORIGINAL STAR 2.0.2 on R 2.15.1.
data(GomezSplitRCBD, package = "STAR", envir = environment())
data(Insecticides, package = "STAR", envir = environment())
rcbd <- subset(GomezSplitRCBD, Variety == "IR5")[, c("Rep", "Nitrogen", "Yield")]

test_that("RCBD: tables match the original STAR report (F = 8.54, p = 0.0022, CV = 9.42)", {
  r <- star_aov(rcbd, "Yield", "Nitrogen", "Rep", design = "RCBD", test = "LSD")
  expect_s3_class(r, "star_aov")
  expect_equal(r$anova$Source, c("Rep", "Nitrogen", "Error", "Total"))
  expect_equal(r$anova$DF, c(2, 5, 10, 17))
  expect_equal(round(r$anova$F[2], 2), 8.54)
  expect_equal(round(r$p_treatment, 4), 0.0022)
  expect_equal(round(r$cv, 2), 9.42)
  expect_equal(round(r$grand_mean, 1), 6155.5)
  expect_equal(r$means$Group, c("b", "a", "a", "a", "a", "a"))
  expect_true(r$significant)
  expect_match(r$summary, "significant effect")
  expect_true(any(grepl("ANOVA TABLE", r$report)))
  # the earlier RCBD-only entry point still works
  expect_equal(star_aov_rcbd(rcbd, "Yield", "Nitrogen", "Rep", test = "LSD")$anova, r$anova)
})

test_that("CRD: Insecticides example matches the original STAR (F = 9.83, CV = 15.09, HSD letters)", {
  r <- star_aov(Insecticides, "GrainYield", "Insecticide", design = "CRD", test = "HSD")
  expect_equal(r$design, "CRD")
  expect_equal(r$anova$Source, c("Insecticide", "Error", "Total"))
  expect_equal(r$anova$DF, c(6, 21, 27))
  expect_equal(round(r$anova$SS[1], 1), 5587174.9)
  expect_equal(round(r$anova$MS[2], 1), 94773.2)
  expect_equal(round(r$anova$F[1], 2), 9.83)
  expect_lt(r$p_treatment, 0.0001)
  expect_equal(round(r$cv, 2), 15.09)
  expect_equal(round(r$grand_mean, 2), 2039.64)
  expect_equal(r$means$Treatment, levels(Insecticides$Insecticide))
  expect_equal(round(r$means$Mean, 2), c(2128.00, 1316.00, 2551.75, 1796.00, 1681.00, 2126.75, 2678.00))
  expect_equal(r$means$Group, c("ab", "c", "a", "bc", "bc", "ab", "a"))
  expect_equal(r$means$N, rep(4, 7))
})

test_that("CRD ignores a block argument and needs no block", {
  r1 <- star_aov(PlantGrowth, "weight", "group", design = "CRD", test = "HSD")
  r2 <- star_aov(PlantGrowth, "weight", "group", block = "group", design = "CRD", test = "HSD")
  expect_equal(r1$anova, r2$anova)
  expect_equal(round(r1$anova$F[1], 2), 4.85)      # original STAR: F = 4.85, p = 0.0159
  expect_equal(round(r1$p_treatment, 4), 0.0159)
})

test_that("every mean-comparison choice works and 'auto' follows STAR's rule", {
  for (tst in c("LSD", "HSD", "duncan", "SNK", "scheffe")) {
    r <- star_aov(rcbd, "Yield", "Nitrogen", "Rep", design = "RCBD", test = tst)
    expect_equal(r$test, tst)
    expect_equal(nrow(r$means), 6)
    expect_true(any(grepl(c(LSD = "Least Significant", HSD = "Tukey", duncan = "Duncan", SNK = "Newman", scheffe = "Scheffe")[[tst]], r$report)))
  }
  expect_equal(star_aov(rcbd, "Yield", "Nitrogen", "Rep", design = "RCBD")$test, "HSD")   # 6 levels > 5
  expect_equal(star_aov(PlantGrowth, "weight", "group")$test, "LSD")                       # 3 levels <= 5
})

test_that("bad input gives clear errors", {
  expect_error(star_aov(rcbd, "Nope", "Nitrogen", "Rep", design = "RCBD"), "not found")
  expect_error(star_aov(rcbd, "Yield", "Yield", "Rep", design = "RCBD"), "different columns")
  expect_error(star_aov(rcbd, "Nitrogen", "Rep", "Yield", design = "RCBD"), "numeric")
  expect_error(star_aov(rcbd, "Yield", "Nitrogen", design = "RCBD"), "needs a block")
  expect_error(star_aov(data.frame(y = 1:4, g = "a"), "y", "g"), "at least two levels")
})

test_that("plot runs without error", {
  for (r in list(star_aov(rcbd, "Yield", "Nitrogen", "Rep", design = "RCBD", test = "LSD"),
                 star_aov(Insecticides, "GrainYield", "Insecticide", design = "CRD"))) {
    f <- tempfile(fileext = ".png"); png(f)
    expect_silent(star_plot_means(r)); dev.off()
    expect_true(file.exists(f)); unlink(f)
  }
})
