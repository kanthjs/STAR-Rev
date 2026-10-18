# The expected/ files are the printed outputs of the ORIGINAL STAR 2.0.2 on R 2.15.1 (see reference/original-outputs).
# Output of the port must match after normalising differences that are not errors:
#  - runs of whitespace (column widths)
#  - numbers rounded to 3 significant digits (R changed the last digits of the Shapiro-Wilk algorithm)
#  - two cosmetic changes in R's own printing (prcomp headings, a row name from t.test)
source("cases.R")
star_case_setup()

normalise <- function(x) {
  x <- gsub("[[:space:]]+", " ", x)
  x <- sub("^ ", "", sub(" $", "", x))
  x <- sub("^Standard deviations [(]1, [.][.], p=4[)]:", "Standard deviations:", x)
  x <- sub("^Rotation [(]n x k[)] = [(]4 x 4[)]:", "Rotation:", x)
  x <- sub("^mean of x ", "1 ", x)
  m <- gregexpr("-?[0-9]+[.][0-9]+", x)
  regmatches(x, m) <- lapply(regmatches(x, m), function(v) format(signif(as.numeric(v), 3), scientific = FALSE))
  x
}

# The original functions leave options(digits, width) changed, and later cases print with those settings, so the
# cases run in sequence inside ONE test_that block (testthat resets options between blocks).
test_that("outputs match the original STAR 2.0.2 (R 2.15.1)", {
  for (nm in names(star_cases)) {
    expected <- readLines(file.path("expected", paste0(nm, ".txt")))
    got <- tryCatch(
      capture.output({ r <- eval(star_cases[[nm]]); if (!is.null(r)) print(r) }),
      error = function(e) paste("ERROR:", conditionMessage(e)))
    expect_equal(normalise(got), normalise(expected), info = nm)
  }
})
