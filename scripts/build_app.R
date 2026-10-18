# Copy the engine sources into the Shiny app so Shinylive can bundle them.
# Usage (from the repo root): Rscript scripts/build_app.R
dest <- file.path("prototype", "app", "star")
unlink(dest, recursive = TRUE)
dir.create(dest, recursive = TRUE)
src <- list.files(file.path("engine", "R"), pattern = "[.]R$", full.names = TRUE)
src <- src[basename(src) != ".onAttach.R"]
stopifnot(all(file.copy(src, dest)))
cat("copied", length(src), "engine files to", dest, "\n")
