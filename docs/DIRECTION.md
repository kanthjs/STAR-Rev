# Direction analysis: how to build on STAR (saved 2026-10-18)

Status: analysis only, not decided. Nothing below has been prototyped.

## Recommendation

Build a **web app whose R engine runs in the user's browser (webR)**, hosted as static files (GitHub Pages). Users open a link, nothing to install, research data never leaves their machine, no server cost. A desktop installer can later be made by wrapping the same web app.

## Why it looks feasible

- `engine/` is pure R (no C/Fortran to compile).
- All 12 dependencies exist in the webR package repo for R 4.5 (checked 2026-10-18 against `repo.r-wasm.org/bin/emscripten/contrib/4.5/PACKAGES`): cluster, lme4, multcomp, fitdistrplus, leaps, vegan, lattice, permute, ade4, R.methodsS3, R.oo, MASS. Also available: lmerTest, agricolae, blocksdesign, FielDHub, shiny, bslib, DT, openxlsx.
- **Not yet tried**: running STAR itself on webR; download size, first-load time and calculation speed are unknown.

## Options compared

| Option | Good when | Weak points |
|---|---|---|
| Desktop installer | no internet, or want the old STAR feel | must bundle R + packages (hundreds of MB); updates are hard (the original STAR's problem) |
| Web + R server | heavy computation, accounts, shared projects | hosting cost, server upkeep, few concurrent R users, data uploaded to a server |
| **Web, R in browser (webR)** | typical trial data (hundreds to ~10k rows) | big first download (cached after), slower than native R, memory limit |
| jamovi module | fastest way to get something usable | tied to jamovi's UI; limited plot-layout/design output |

Recommendation changes toward desktop/server if users have almost no internet, or datasets are very large.

## Work needed whichever way

1. Separate computation from printing: STAR functions `cat()` text tables (the old GUI used `sink`). A modern UI needs tables/objects back, to render, export to Excel/Word, and plot interactively.
2. Keep `scripts/compare_cases.R` as the regression net while refactoring.
3. Replace DiGGer (4 design functions blocked: designAlphaLattice, designLatinizedAlpha, designLatinizedRowCol, designRowColumn), e.g. with blocksdesign, and verify equivalence.

## Positioning

Basic statistics already exist in free modern tools (jamovi, JASP). STAR's niche is agricultural experiments: randomisation with field layouts, split/strip/lattice ANOVA, multi-location analysis. Compare against FielDHub (Shiny app for field-trial design) before investing.

## Suggested first step

Small Shiny prototype with two screens (RCBD randomisation, RCBD ANOVA) exported with Shinylive to GitHub Pages; open it on a typical user laptop and measure load time and speed before committing to the full build.

## Open questions

- Who are the users and how good is their internet?
- Typical dataset size?
- Need for saved projects / sharing between users (pushes toward a server)?
- Which analyses matter most (designs vs ANOVA vs multivariate)?
