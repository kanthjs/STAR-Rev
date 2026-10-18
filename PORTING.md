# STAR: reverse-engineering and porting notes

Target: IRRI **STAR** (Statistical Tool for Agricultural Research), installed at `C:\STAR`.
Goal: reuse the R statistics engine on a modern R (option "A": wrap the R package behind an API later).

## Layout

| Path | What |
|---|---|
| `input/` | Copies of the original `Star_1.0.0.201401091026.jar` and the jars inside it (originals untouched) |
| `tools/cfr-0.152.jar` | CFR decompiler (runs on the bundled Java 6) |
| `src/` | Decompiled Java GUI (297 classes, `org.irri.breedingtool.*`) |
| `src-rjavamanager/` | Decompiled `RJavaManager1.0.jar`: the Java-to-R bridge |
| `src-R/` | All 257 functions of the R package STAR 2.0.2, one `.R` per function, pristine (from `deparse`, comments lost) |
| `star-r/` | Installable port of the R package for R 4.5.2 (edits listed below) |
| `scripts/` | `extract_star.R`, `gen_namespace.R`, `install_deps.R`, `smoke.R`, trace helpers |

## How the program works

- Eclipse E4 RCP (Java 6, OSGi). Menu item -> `*Handler` (`@Execute`, checks a project and a data tab are active) -> `*Dialog` -> a manager in `RJavaManager1.0.jar`.
- Managers build R command strings and call `Rengine.eval` (JRI). Pattern: `read.csv` into `dataRead`, `sink(out.txt)`, `result <- try(Fn(data = "dataRead", ...))`, then `write.csv` or an error message, then `rm(list=ls())` and `rEngine.end()`.
- `initStar()` runs `library(STAR)`; `initPBtool()` runs `library(PBTools)` (PBTools not analysed).
- Note the data argument is the **name** of a data frame as a string (`data = "dataRead"`); the `.default` methods also accept a data frame.

## Changes made in `star-r/` (relative to the recovered sources)

1. **Package skeleton**: wrote `DESCRIPTION` (Depends R >= 4.0, Imports listed), copied the 56 `.rda` datasets from the old install, kept the original `NAMESPACE` as `NAMESPACE.orig`.
2. **S3 registration** (`scripts/gen_namespace.R`): modern R no longer finds unregistered S3 methods inside a namespace, so every generic failed with "no applicable method". The script finds generics (bodies calling `UseMethod`) and writes 104 `S3method()` lines, plus `import(stats, graphics, grDevices, utils)`. Re-run it after adding methods.
3. **`R/zzz_compat.R`**: shims masking `data.frame`, `read.csv`, `read.table`, `read.delim` inside the namespace with `stringsAsFactors = TRUE`. Needed because R 4.0 changed the default; e.g. `NormalityTest` relied on `levels()` of a character column and produced a column named `NA`. This is a blunt fix; individual call sites could be changed instead.
4. **`PCA.default.R`**: added `matx <- match.arg(matx)`. Since R 4.2 `if (c("corr","cov") == "corr")` is an error (the old Java caller always passed one value).

Dependencies installed from CRAN into the user library `C:\Users\Lenovo_01\AppData\Local\R\win-library\4.5`: lme4, multcomp, fitdistrplus, leaps, vegan, permute, ade4, R.methodsS3, R.oo (plus their dependencies).

## Status (`scripts/smoke.R`, R 4.5.2)

Passing (runs without error; **output numbers not yet compared with the original**): DescriptiveStatistics, CompareMeans (independent), NormalityTest, Crosstab, AOVTest (RCBD, SplitRCBD, LSD), LinearRegressionAnalysis, BivariateCorrelationTest, PCA (graphs off), ClusterKmeans (graph off), designRCBD, designCRD, designLSD, designBIBD, designLattice, designSplit.

Not covered by tests: most of the other ~230 functions, all graph functions, `combAOVTest`, `ssaTest`, MANOVA, discriminant, non-linear regression, distribution fitting, file output (`sink`, csv, png).

Findings on the two items that looked broken:
- `designLattice`: not a bug. It needs `numFieldRow` to be a multiple of sqrt(number of treatments) (GUI supplies it); default 1 fails with "subscript out of bounds". A friendlier error would be a worthwhile fix.
- `designAlphaLattice`: not run. It calls **DiGGer**.

## Known blocker: DiGGer

- `DiGGer` 0.2-3 (2011, Neil Coombes, NSW DPI) is not on CRAN (not in the index, `Archive/DiGGer/` returns 404). The old install has only a Windows binary built for R 2.14 (`C:\RPackages\R-2.15.1\library\DiGGer`).
- Its DESCRIPTION license is "NSW DPI Freeware Licence" (not GPL); the stated source URL `austatgen.org/files/software/downloads` returned HTTP 403 when probed. Redistribution terms are unverified.
- Affected STAR functions (they reference DiGGer): `designAlphaLattice`, `designLatinizedAlpha`, `designLatinizedRowCol`, `designRowColumn`.
- Not obtainable for modern R so far; the old binary will not load in R 4.5. Options later: find source from the author, or replace with another optimal-design generator (e.g. `agricolae`, `blocksdesign`) and compare outputs.

## Licensing

STAR R package: GPL (>= 2), so derivatives must stay GPL. The Java GUI's license was not found in the jar; check with IRRI before redistributing. DiGGer: see above.

## Suggested next steps

1. Build a regression suite: run the original R 2.15.1 and the port on the same inputs (the datasets in `star-r/data`) and diff the outputs.
2. Widen smoke coverage to every exported function.
3. Wrap the port in an HTTP API (e.g. plumber) returning structured results instead of `sink` text.
4. Decide what to do about DiGGer.

## Update: additional fixes found by comparing against R 2.15.1

The earlier smoke test only checked "no error", which hid wrong output (e.g. AOVTest printed "The data set does not contain any observations" and still returned). Comparison against the original is the real test: `scripts/compare_cases.R <outdir>` run on both R versions, outputs in `compare/old` (R 2.15.1, original packages) and `compare/new` (R 4.5.2 port).

5. **`AOVTest.default.R`, `BIBDTest.default.R`**: `cbind(data, setVar = "1")` now yields a character column (R 4.0), so `nlevels()` was 0 and no analysis ran. Changed to `factor("1")`.
6. **`R/zzz_compat.R`**: `nchar()` wrapper. R < 3.3 returned 2 for `NA`; newer returns `NA`, which made `sum(colwidth)` NA and broke `printAOVTable` ("invalid 'times' argument") in every ANOVA/regression table. The wrapper passes `keepNA = FALSE`.

Run the comparison with:
```
scripts/compare_cases.R compare/old   # with C:\RPackages\R-2.15.1\bin\i386\Rscript.exe, R_LIBS_USER unset
scripts/compare_cases.R compare/new   # with R 4.5.2 and R_LIBS_USER pointing at the user library
```

### Result (27 cases)

Identical output: DescriptiveStatistics (x2), CompareMeans independent/paired, Correlation (Pearson, Spearman), Crosstab, LinearRegressionAnalysis (incl. CI, covariance, VIF), HeteroskedasticityTest, skewness/kurtosis helpers, KruskalWallisTest.

Differences that are not errors in the port:
- trailing whitespace in lines printed by R's own `print.summary.lm`/`cat` (AOV*, 4-10 lines each);
- last digits of the Shapiro-Wilk statistic/p-value (R fixed the algorithm in 4.x; e.g. 0.04880736 vs 0.04880824);
- `prcomp` print headings (`Rotation (n x k) = (4 x 4):`) and a row name (`mean of x`) in CompareMeans one-sample;
- k-means cluster numbering differs because `set.seed(1)` + `sample()` changed in R 3.6; cluster contents are the same sets with permuted labels. Any randomised output (all `design*` randomisations) will differ for the same seed.

Not verified: `ClusterAgglo`, `Discriminant`, `FitDistrib` fail with the same error on both R versions with my test arguments (`memSummary` / `tempData` not found / `EXPR must be a length 1 vector`); either my calls are incomplete or these are original bugs. Needs checking against how `STARAnalysisManager.java` calls them. Not compared at all: graph functions, designs (random), combAOVTest, ssaTest, MANOVA, nonlinear regression, PowerTransform, data-manipulation functions.
