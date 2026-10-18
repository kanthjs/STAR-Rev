# Structured API on top of AOVTest. Reference values come from the ORIGINAL STAR 2.0.2 on R 2.15.1
# (see reference/original-outputs and engine/tests/testthat/expected).
data(GomezSplitRCBD, package = "STAR", envir = environment())
data(Insecticides, package = "STAR", envir = environment())
rcbd <- subset(GomezSplitRCBD, Variety == "IR5")[, c("Rep", "Nitrogen", "Yield")]
tg <- ToothGrowth; tg$dose <- factor(tg$dose)

test_that("RCBD one factor: tables match the original STAR report (F = 8.54, p = 0.0022, CV = 9.42)", {
  r <- star_aov(rcbd, "Yield", "Nitrogen", "Rep", design = "RCBD", test = "LSD")
  expect_s3_class(r, "star_aov")
  expect_equal(r$anova$Source, c("Rep", "Nitrogen", "Error", "Total"))
  expect_equal(r$anova$DF, c(2, 5, 10, 17))
  expect_equal(round(r$anova$F[2], 2), 8.54)
  expect_equal(round(r$p_treatment, 4), 0.0022)
  expect_equal(round(r$cv, 2), 9.42)
  expect_equal(round(r$grand_mean, 1), 6155.5)
  expect_equal(names(r$means), "Nitrogen")
  expect_equal(r$means$Nitrogen$Group, c("b", "a", "a", "a", "a", "a"))
  expect_true(r$significant)
  expect_match(r$summary, "significant effect")
  expect_true(any(grepl("ANOVA TABLE", r$report)))
  expect_equal(star_aov_rcbd(rcbd, "Yield", "Nitrogen", "Rep", test = "LSD")$anova, r$anova)
})

test_that("CRD one factor: Insecticides matches the original STAR (F = 9.83, CV = 15.09, HSD letters)", {
  r <- star_aov(Insecticides, "GrainYield", "Insecticide", design = "CRD", test = "HSD")
  expect_equal(r$design, "CRD")
  expect_equal(r$anova$Source, c("Insecticide", "Error", "Total"))
  expect_equal(r$anova$DF, c(6, 21, 27))
  expect_equal(round(r$anova$SS[1], 1), 5587174.9)
  expect_equal(round(r$anova$F[1], 2), 9.83)
  expect_lt(r$p_treatment, 0.0001)
  expect_equal(round(r$cv, 2), 15.09)
  m <- r$means$Insecticide
  expect_equal(m$Treatment, levels(Insecticides$Insecticide))
  expect_equal(round(m$Mean, 2), c(2128.00, 1316.00, 2551.75, 1796.00, 1681.00, 2126.75, 2678.00))
  expect_equal(m$Group, c("ab", "c", "a", "bc", "bc", "ab", "a"))
  expect_equal(m$N, rep(4, 7))
})

test_that("RCBD two factors (Nitrogen x Variety) match the original STAR", {
  r <- star_aov(GomezSplitRCBD, "Yield", c("Nitrogen", "Variety"), "Rep", design = "RCBD")
  expect_equal(r$anova$Source, c("Rep", "Nitrogen", "Variety", "Nitrogen:Variety", "Error", "Total"))
  expect_equal(r$anova$DF, c(2, 5, 3, 15, 46, 71))
  expect_equal(round(r$anova$SS[4], 4), 69343486.9306)
  expect_equal(round(r$anova$F[2:4], 2), c(19.99, 98.42, 15.18))
  expect_equal(round(r$cv, 2), 10.07)
  expect_equal(round(r$grand_mean, 2), 5478.90)
  expect_true(r$interaction_significant)
  expect_equal(r$effects$Effect, c("Nitrogen", "Variety", "Nitrogen:Variety"))
  expect_true(all(is.na(r$means$Nitrogen$Group)))        # main effects are not compared when the interaction is significant
  expect_equal(r$tests_used[["Nitrogen"]], "HSD")        # 6 levels
  expect_equal(r$tests_used[["Variety"]], "LSD")         # 4 levels
  expect_match(r$summary, "interaction is significant")
  expect_equal(nrow(r$cells), 24)
  # Nitrogen at each level of Variety (HSD), letters for N = 1..6
  sm <- r$simple[["Nitrogen within Variety"]]
  expect_equal(sm$Group[sm$Variety == "C4-63"], c("b", "a", "a", "a", "a", "a"))
  expect_equal(sm$Group[sm$Variety == "IR8"], c("d", "c", "bc", "bc", "ab", "a"))
  expect_equal(sm$Group[sm$Variety == "Peta"], c("a", "a", "a", "a", "b", "b"))
  expect_equal(round(sm$Mean[sm$Variety == "IR8"], 4), c(4252.6667, 5672.0000, 6400.0000, 6732.6667, 7563.3333, 8700.6667))
  # Variety at each level of Nitrogen (LSD), letters for C4-63, IR5, IR8, Peta
  sv <- r$simple[["Variety within Nitrogen"]]
  expect_equal(sv$Group[sv$Nitrogen == "0"], c("b", "a", "a", "a"))
  expect_equal(sv$Group[sv$Nitrogen == "60"], c("ab", "a", "ab", "b"))
  expect_equal(sv$Group[sv$Nitrogen == "180"], c("b", "b", "a", "c"))
  expect_equal(attr(sv, "test"), "LSD")
})

test_that("CRD two factors (supp x dose) match the original STAR", {
  r <- star_aov(tg, "len", c("supp", "dose"), design = "CRD")
  expect_equal(r$anova$Source, c("supp", "dose", "supp:dose", "Error", "Total"))
  expect_equal(r$anova$DF, c(1, 2, 2, 54, 59))
  expect_equal(round(r$anova$F[1:3], 2), c(15.57, 92.00, 4.11))
  expect_equal(round(r$p_treatment, 4), 0.0002)
  expect_equal(round(r$cv, 2), 19.30)
  expect_equal(round(r$grand_mean, 2), 18.81)
  expect_true(r$interaction_significant)
  s1 <- r$simple[["supp within dose"]]                    # LSD
  expect_equal(s1$Group[s1$dose == "0.5"], c("a", "b"))
  expect_equal(s1$Group[s1$dose == "2"], c("a", "a"))
  s2 <- r$simple[["dose within supp"]]
  expect_equal(s2$Group[s2$supp == "OJ"], c("c", "b", "a"))
  expect_equal(round(s2$Mean[s2$supp == "VC"], 4), c(7.98, 16.77, 26.14))
})

test_that("without a significant interaction the main effects are compared instead", {
  set.seed(1)
  d <- expand.grid(A = c("a1", "a2"), B = c("b1", "b2", "b3"), rep = 1:4)
  d$y <- 10 + 3 * (d$A == "a2") + as.integer(factor(d$B)) * 0 + rnorm(nrow(d), sd = 0.5)
  r <- star_aov(d, "y", c("A", "B"), design = "CRD")
  expect_false(r$interaction_significant)
  expect_null(r$simple)
  expect_true(r$effects$Significant[r$effects$Effect == "A"])
  expect_false(any(is.na(r$means$A$Group)))
  expect_match(r$summary, "interaction is not significant")
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
    expect_equal(r$tests_used[["Nitrogen"]], tst)
    expect_equal(nrow(r$means$Nitrogen), 6)
    expect_true(any(grepl(c(LSD = "Least Significant", HSD = "Tukey", duncan = "Duncan", SNK = "Newman", scheffe = "Scheffe")[[tst]], r$report)))
  }
  expect_equal(star_aov(rcbd, "Yield", "Nitrogen", "Rep", design = "RCBD")$tests_used[["Nitrogen"]], "HSD")   # 6 levels > 5
  expect_equal(star_aov(PlantGrowth, "weight", "group")$tests_used[["group"]], "LSD")                          # 3 levels <= 5
})

test_that("bad input gives clear errors", {
  expect_error(star_aov(rcbd, "Nope", "Nitrogen", "Rep", design = "RCBD"), "not found")
  expect_error(star_aov(rcbd, "Yield", "Yield", "Rep", design = "RCBD"), "different columns")
  expect_error(star_aov(rcbd, "Nitrogen", "Rep", "Yield", design = "RCBD"), "numeric")
  expect_error(star_aov(rcbd, "Yield", "Nitrogen", design = "RCBD"), "needs a block")
  expect_error(star_aov(data.frame(y = 1:4, g = "a"), "y", "g"), "at least two levels")
  expect_error(star_aov(tg, "len", c("supp", "dose", "len")), "different columns|Only one or two")
  expect_error(star_aov(tg[!(tg$supp == "OJ" & tg$dose == "0.5"), ], "len", c("supp", "dose")), "no observations")
})

test_that("plots run without error", {
  one <- star_aov(Insecticides, "GrainYield", "Insecticide", design = "CRD")
  two <- star_aov(GomezSplitRCBD, "Yield", c("Nitrogen", "Variety"), "Rep", design = "RCBD")
  f <- tempfile(fileext = ".png"); png(f)
  expect_silent(star_plot_means(one))
  expect_silent(star_plot_means(two, "Nitrogen"))
  expect_silent(star_plot_means(two, "interaction"))
  expect_error(star_plot_means(two, "nope"), "Unknown effect")
  dev.off(); unlink(f)
})
