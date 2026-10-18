# STAR web prototype (Shinylive)

Two screens, running the recovered STAR R engine (`app/star/*.R`, copied from `star-r/R`) inside the browser via webR:

1. Randomise RCBD (`designRCBD`) with field book download.
2. ANOVA for RCBD (`AOVTest`) with LSD/Tukey/Duncan.

Local test (native R): `Rscript prototype/test_server.R`.
Export: `Rscript -e 'shinylive::export("prototype/app", "prototype/site")'`, then serve `prototype/site` over http (needs a service worker, so use a normal browser; the Claude desktop browser pane refuses to register it).
Deploy: `.github/workflows/pages.yml` builds and publishes to GitHub Pages (enable Pages -> Source: GitHub Actions in the repo settings).
After changing `star-r/R`, re-copy the sources into `prototype/app/star/`.
