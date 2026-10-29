# Text of the Methods page: what each analysis does, and the references for the methods (not for the software; the
# software references are built from the installed packages in app.R). Keep this in step with the engine code:
# engine/R/star_aov.R, star_splitplot.R, star_helpers.R, star_diagnostics.R, star_correlation.R, star_regression.R.

methods_sections <- tagList(
  h4("Analysis of variance: completely randomized design (CRD)"),
  p("Model (all effects fixed): ", code("y ~ A"), " for one factor, ", code("y ~ A * B"), " for two factors with their interaction, fitted by ",
    code("aov()"), " (Chambers et al., 1992). Each effect is tested with F = MS(effect) / MS(error). Rows with a missing response are left out. ",
    "The tables use sequential (Type I) sums of squares, as ", code("aov()"), " does: with two factors and unequal numbers of observations, the result for the second factor depends on the order in which the factors are listed."),
  p("CV (%) = 100 x sqrt(MS error) / grand mean. Partial eta-squared = SS(effect) / (SS(effect) + SS(error)). ",
    "In the means tables, SE is the standard deviation of the group divided by the square root of its number of observations."),
  p("Textbook description: Gomez & Gomez (1984)."),

  h4("Analysis of variance: randomized complete block design (RCBD)"),
  p("Model: ", code("y ~ Block + A"), " or ", code("y ~ Block + A * B"), ". Blocks are a fixed effect, as in the classic STAR analysis. ",
    "If every block x treatment combination has the same number of observations and no response is missing, this model is fitted directly with ", code("aov()"),
    ". Otherwise the original STAR procedure is used: it estimates missing values and adjusts the degrees of freedom (Gomez & Gomez, 1984), and it refuses layouts it cannot analyse."),

  h4("Analysis of variance: split plot in RCBD"),
  p("Model: ", code("y ~ Block + A * B + Error(Block:A)"), " fitted with ", code("aov()"), " (Chambers et al., 1992), where A is the main-plot factor and B the sub-plot factor. ",
    "Error(a) (blocks x A) tests Block and A; Error(b) (the residual) tests B and A x B. CV(a) and CV(b) are computed from the two error mean squares. ",
    "The layout must be complete and balanced: one observation for each block x A x B combination."),
  p("Means comparison in a split plot: A is compared with Error(a) and B with Error(b); standard errors come from the model, sqrt(MS error / n). ",
    "When the interaction is significant, B is compared within each level of A with Error(b), and A is compared within each level of B with the pooled error ",
    "[MS(a) + (b - 1) MS(b)] / b, whose degrees of freedom are obtained by Satterthwaite's approximation (Satterthwaite, 1946; Gomez & Gomez, 1984) and rounded down."),

  h4("Comparison of means (post hoc tests)"),
  p("Fisher's protected LSD (Fisher, 1935), Tukey's HSD (Tukey, 1949; Tukey-Kramer, Kramer, 1956, when group sizes differ) and Duncan's multiple range test, DMRT (Duncan, 1955), ",
    "are computed with the agricolae package using the error mean square and degrees of freedom of the relevant error term, at the significance level chosen in the app. ",
    "Means that share a letter are not significantly different. Letters are shown only when the F test of the effect is significant. ",
    "With two factors and a significant interaction, simple effects are compared (each factor at each level of the other) instead of the overall means. ",
    "Automatic = LSD when a factor has at most 5 levels, otherwise Tukey HSD. How each routine treats unequal numbers of replications is described in the agricolae documentation."),

  h4("Checks of the assumptions (ANOVA and regression)"),
  p("The same linear model is refitted and its residuals examined (not available for split-plot designs yet). ",
    "Outliers: externally studentized residuals, |r| > 3 is an outlier and 2 < |r| <= 3 is suspect (Belsley et al., 1980); Cook's distance (Cook, 1977) is listed for flagged rows. ",
    "Normality of residuals: Shapiro-Wilk test (Shapiro & Wilk, 1965) and Kolmogorov-Smirnov test with Lilliefors correction (Lilliefors, 1967). ",
    "Equal variances in the ANOVA: Levene test with medians, also called Brown-Forsythe (Brown & Forsythe, 1974), across treatment cells. ",
    "In regression, constant variance: Breusch-Pagan test (Breusch & Pagan, 1979) in its studentized form (Koenker, 1981) against the fitted values. ",
    "These tests are advice, not proof: with small samples they have little power and with large samples they flag trivial departures."),

  h4("Correlation"),
  p("Pearson, Spearman or Kendall correlation of each pair of variables with ", code("cor.test()"), " (Pearson, 1895; Spearman, 1904; Kendall, 1938; Hollander & Wolfe, 1973; ",
    "p values for Spearman's rho: Best & Roberts, 1975). Each pair uses the rows where both variables are present, and N is shown per pair. ",
    "The confidence interval of Pearson's r uses Fisher's z transformation (Fisher, 1921). The optional adjustment of the p values for many pairs uses ",
    code("p.adjust()"), ": Holm (1979), Bonferroni, or Benjamini-Hochberg (1995). ",
    "The words weak, moderate and strong are rules of thumb of this app (|r| below 0.1 negligible, below 0.3 weak, below 0.5 moderate, below 0.7 strong, otherwise very strong), not a statistical test."),

  h4("Regression"),
  p("Ordinary least squares with ", code("lm()"), " (Wilkinson & Rogers, 1973; Chambers, 1992): a straight line, or a quadratic or cubic polynomial in one predictor using the powers x, x^2, x^3 as terms, or a multiple regression with several predictors. ",
    "The model F test, R-squared, adjusted R-squared, root mean square error, CV and AIC (Akaike, 1974) are reported, and t tests and confidence intervals for each coefficient. ",
    "Standardized coefficients (straight-line models only) are b x SD(x) / SD(y). The variance inflation factor (VIF) of a predictor is the corresponding diagonal element of the inverse of the correlation matrix of the predictors; a value above 10 is flagged (Belsley et al., 1980). ",
    "For a quadratic, the stationary point is -b1 / (2 b2), and it is marked as an extrapolation if it lies outside the range of the data. ",
    "Predictions (one predictor) are fitted means with their 95% confidence interval, and values outside the range of the data are marked as extrapolations."),

  h4("Data handling"),
  p("Uploaded files are read in the browser. Numbers written with thousands separators or units (for example 1,250.5 or 12 kg) are converted; entries such as n/a are treated as missing, and the Data checks box on each screen lists what was changed or left out.")
)

# references for the methods above (software references are generated from the packages in app.R)
method_refs <- c(
  "Akaike H (1974). A new look at the statistical model identification. IEEE Transactions on Automatic Control, 19(6), 716-723. https://doi.org/10.1109/TAC.1974.1100705",
  "Belsley DA, Kuh E, Welsch RE (1980). Regression Diagnostics: Identifying Influential Data and Sources of Collinearity. Wiley, New York.",
  "Benjamini Y, Hochberg Y (1995). Controlling the false discovery rate: a practical and powerful approach to multiple testing. Journal of the Royal Statistical Society B, 57(1), 289-300. https://doi.org/10.1111/j.2517-6161.1995.tb02031.x",
  "Best DJ, Roberts DE (1975). Algorithm AS 89: the upper tail probabilities of Spearman's rho. Applied Statistics, 24(3), 377-379.",
  "Breusch TS, Pagan AR (1979). A simple test for heteroscedasticity and random coefficient variation. Econometrica, 47(5), 1287-1294. https://doi.org/10.2307/1911963",
  "Brown MB, Forsythe AB (1974). Robust tests for the equality of variances. Journal of the American Statistical Association, 69(346), 364-367. https://doi.org/10.1080/01621459.1974.10482955",
  "Chambers JM (1992). Linear models. Chapter 4 in Chambers JM, Hastie TJ (eds), Statistical Models in S. Wadsworth & Brooks/Cole, Pacific Grove.",
  "Chambers JM, Freeny A, Heiberger RM (1992). Analysis of variance; designed experiments. Chapter 5 in Chambers JM, Hastie TJ (eds), Statistical Models in S. Wadsworth & Brooks/Cole, Pacific Grove.",
  "Cook RD (1977). Detection of influential observation in linear regression. Technometrics, 19(1), 15-18. https://doi.org/10.1080/00401706.1977.10489493",
  "Duncan DB (1955). Multiple range and multiple F tests. Biometrics, 11(1), 1-42. https://doi.org/10.2307/3001478",
  "Fisher RA (1921). On the 'probable error' of a coefficient of correlation deduced from a small sample. Metron, 1(4), 3-32.",
  "Fisher RA (1935). The Design of Experiments. Oliver and Boyd, Edinburgh.",
  "Gomez KA, Gomez AA (1984). Statistical Procedures for Agricultural Research, 2nd edition. Wiley, New York.",
  "Hollander M, Wolfe DA (1973). Nonparametric Statistical Methods. Wiley, New York.",
  "Holm S (1979). A simple sequentially rejective multiple test procedure. Scandinavian Journal of Statistics, 6(2), 65-70.",
  "Kendall MG (1938). A new measure of rank correlation. Biometrika, 30(1-2), 81-93. https://doi.org/10.2307/2332226",
  "Koenker R (1981). A note on studentizing a test for heteroscedasticity. Journal of Econometrics, 17(1), 107-112. https://doi.org/10.1016/0304-4076(81)90062-2",
  "Kramer CY (1956). Extension of multiple range tests to group means with unequal numbers of replications. Biometrics, 12(3), 307-310. https://doi.org/10.2307/3001469",
  "Lilliefors HW (1967). On the Kolmogorov-Smirnov test for normality with mean and variance unknown. Journal of the American Statistical Association, 62(318), 399-402. https://doi.org/10.1080/01621459.1967.10482916",
  "Pearson K (1895). Notes on regression and inheritance in the case of two parents. Proceedings of the Royal Society of London, 58, 240-242.",
  "Satterthwaite FE (1946). An approximate distribution of estimates of variance components. Biometrics Bulletin, 2(6), 110-114. https://doi.org/10.2307/3002019",
  "Shapiro SS, Wilk MB (1965). An analysis of variance test for normality (complete samples). Biometrika, 52(3-4), 591-611. https://doi.org/10.2307/2333709",
  "Spearman C (1904). The proof and measurement of association between two things. The American Journal of Psychology, 15(1), 72-101. https://doi.org/10.2307/1412159",
  "Tukey JW (1949). Comparing individual means in the analysis of variance. Biometrics, 5(2), 99-114. https://doi.org/10.2307/3001913",
  "Wilkinson GN, Rogers CE (1973). Symbolic description of factorial models for analysis of variance. Applied Statistics, 22(3), 392-399. https://doi.org/10.2307/2346786"
)
