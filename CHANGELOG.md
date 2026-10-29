# Changelog

Changes to the STAR web app that can affect results are marked **(results)**. The version is shown in the app's About tab and in every downloaded file.

## 0.1.0 (unreleased)
- First public build: ANOVA (CRD, RCBD, split plot), correlation and linear regression.
- Only the engine files the app uses are bundled, so the app opens faster.
- About tab with license, citation and problem-reporting link; app version written to Excel and TXT downloads.
- Home page ("What do you want to do?"), Methods page with a copyable methods sentence, collapsible "What does this mean?" help, maintainer and disclaimer in About, app name kept in one place (`app_name`), deployment notes for IT in `docs/DEPLOY.md`.
- Excel files are written with writexl and read with readxl (instead of openxlsx and its large dependency stringi): about 12 MB less to download when the app opens.
- Mean comparison (LSD, Tukey, Duncan, SNK, Scheffe, Bonferroni) now uses the agricolae package; the normality test uses nortest and the Breusch-Pagan test uses lmtest (identical numbers to the old code). The Methods page builds the reference list from each package's citation.
- The mean comparison choices in the app are limited to LSD, Tukey HSD and Duncan (DMRT), plus Automatic (SNK, Scheffe and Bonferroni remain available in the R engine).
- **(results)** Scheffe letters were wrong in earlier builds: the alpha chosen in the app was ignored (always 0.05) and the letters were as for Tukey's test. Fixed in the tables (via agricolae) and in the classic STAR report (the recovered code used F instead of sqrt((k-1)F) as the critical value). Re-run any Scheffe comparison made before this version.
- **(results)** With unbalanced data, the N column shows each group's real count instead of the harmonic mean; Tukey uses the Tukey-Kramer method when group sizes differ.
- Methods page now explains what each analysis does (models for CRD, RCBD and split plot, means comparison, assumption checks, correlation, regression) with the matching references.
