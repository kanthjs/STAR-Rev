# STAR web prototype (Shinylive)

One screen: ANOVA for a randomized complete block design (`AOVTest`) with mean comparison (LSD / Tukey / Duncan / SNK / Scheffe), copy and download of the output. It runs the recovered STAR engine inside the browser via webR.

Build and test:

```
Rscript scripts/build_app.R                 # copies engine/R into prototype/app/star (git-ignored)
Rscript prototype/tests/test_server.R       # headless test of the server logic on native R
Rscript -e 'shinylive::export("prototype/app", "prototype/site")'
```

Serve `prototype/site` over http with a normal browser (it needs a service worker; the Claude desktop browser pane refuses to register one).
Deploy: `.github/workflows/pages.yml` builds and publishes to GitHub Pages (repo settings -> Pages -> Source: GitHub Actions).
