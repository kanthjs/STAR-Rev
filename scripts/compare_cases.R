# Usage: Rscript scripts/compare_cases.R <outdir>
# Runs the shared cases (engine/tests/testthat/cases.R) with whatever STAR is installed and writes the
# printed output of each case to <outdir>/<case>.txt. Used on R 2.15.1 to create reference/original-outputs.
outdir <- commandArgs(TRUE)[1]
dir.create(outdir, recursive = TRUE, showWarnings = FALSE)
here <- dirname(normalizePath(sub("--file=", "", grep("--file=", commandArgs(FALSE), value = TRUE))))
suppressMessages(library(STAR))
source(file.path(here, "..", "engine", "tests", "testthat", "cases.R"))
star_case_setup()
for (nm in names(star_cases)) {
  txt <- tryCatch(
    capture.output({ r <- eval(star_cases[[nm]]); if (!is.null(r)) print(r) }),
    error = function(e) paste("ERROR:", conditionMessage(e)))
  writeLines(txt, file.path(outdir, paste0(nm, ".txt")))
  cat(sprintf("%-18s %s\n", nm, if (length(txt) && grepl("^ERROR", txt[1])) txt[1] else paste(length(txt), "lines")))
}
