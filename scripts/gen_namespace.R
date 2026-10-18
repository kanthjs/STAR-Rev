# Regenerate the NAMESPACE of the engine package from its R sources.
# Usage: Rscript scripts/gen_namespace.R engine
# - S3method() registrations for every method of a generic (modern R does not find unregistered methods)
# - export() of everything the original exported (names starting with a letter) EXCEPT the compatibility shims
#   in zzz_compat.R, so attaching STAR does not mask base functions.
args <- commandArgs(TRUE); pkg <- args[1]
files <- list.files(file.path(pkg, "R"), full.names = TRUE, all.files = TRUE, no.. = TRUE)
e <- new.env(); for (f in files) sys.source(f, envir = e)
nm <- ls(e, all.names = TRUE)
isfun <- vapply(nm, function(n) is.function(get(n, e)), logical(1))
isgen <- vapply(nm, function(n) { o <- get(n, e); is.function(o) && any(grepl("UseMethod", deparse(body(o)), fixed = TRUE)) }, logical(1))
shims <- c("data.frame", "read.csv", "read.table", "read.delim", "nchar")
exports <- setdiff(nm[isfun & grepl("^[[:alpha:]]", nm)], shims)
s3 <- character()
for (g in nm[isgen]) for (m in nm[startsWith(nm, paste0(g, "."))]) s3 <- c(s3, sprintf("S3method(\"%s\", \"%s\")", g, substring(m, nchar(g) + 2)))
base <- readLines(file.path(pkg, "NAMESPACE.orig"))
base <- base[!grepl("^S3method|^exportPattern", base)]
writeLines(c(base, "import(stats)", "import(graphics)", "import(grDevices)", "import(utils)",
             sprintf("export(\"%s\")", exports), unique(s3)), file.path(pkg, "NAMESPACE"))
cat(sum(isgen), "generics;", length(unique(s3)), "S3 registrations;", length(exports), "exports\n")
