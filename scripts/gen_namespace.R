# Regenerate S3method() registrations for the recovered STAR sources.
args <- commandArgs(TRUE); pkg <- args[1]
files <- list.files(file.path(pkg, "R"), full.names = TRUE, all.files = TRUE, no.. = TRUE)
e <- new.env(); for (f in files) sys.source(f, envir = e)
nm <- ls(e, all.names = TRUE)
isgen <- vapply(nm, function(n) { o <- get(n, e); is.function(o) && any(grepl("UseMethod", deparse(body(o)), fixed = TRUE)) }, logical(1))
gens <- nm[isgen]
lines <- character()
for (g in gens) for (m in nm[startsWith(nm, paste0(g, "."))]) lines <- c(lines, sprintf("S3method(\"%s\", \"%s\")", g, substring(m, nchar(g) + 2)))
base <- readLines(file.path(pkg, "NAMESPACE.orig"))
base <- base[!grepl("^S3method", base)]
# keep orig non-S3 lines; add ours, drop duplicates
writeLines(c(base, "import(stats)", "import(graphics)", "import(grDevices)", "import(utils)", unique(lines)), file.path(pkg, "NAMESPACE"))
cat(length(gens), "generics;", length(unique(lines)), "S3 registrations\n")
