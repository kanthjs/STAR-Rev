## New API (not part of the original STAR): read an uploaded data file (.csv, .tsv, .txt, .xlsx) into a data frame
## whose columns are all text, so that star_prepare_data() decides what is numeric. Delimiter (, ; tab |) and
## encoding (UTF-8, with or without BOM, else Latin-1) are detected. Excel files are read with readxl.

star_read_table <- function(path, name = path) {
  ext <- tolower(sub(".*[.]", "", name))
  df <- if (ext %in% c("xlsx", "xlsm")) {
    if (!requireNamespace("readxl", quietly = TRUE)) stop("Reading Excel files needs the 'readxl' package.")
    as.data.frame(readxl::read_excel(path, sheet = 1, col_names = TRUE, col_types = "text", .name_repair = "minimal"))
  } else if (ext == "xls") {
    stop("Old .xls files are not supported; save the sheet as .xlsx or .csv.")
  } else {
    first <- readLines(path, n = 5, warn = FALSE, encoding = "bytes")
    first <- first[nzchar(trimws(first))]
    if (!length(first)) stop("The file is empty.")
    cnt <- vapply(c(",", ";", "\t", "|"), \(s) sum(lengths(regmatches(first[1], gregexpr(s, first[1], fixed = TRUE)))), 0)
    sep <- if (max(cnt) == 0) "," else names(cnt)[which.max(cnt)]
    read <- function(enc) {
      read.table(path,
        header = TRUE, sep = sep, quote = "\"", colClasses = "character", comment.char = "",
        strip.white = TRUE, na.strings = c("", "NA"), check.names = FALSE, fill = TRUE,
        fileEncoding = enc
      )
    }
    utf8 <- validUTF8(readLines(path, warn = FALSE, encoding = "bytes"))
    read(if (all(utf8)) "UTF-8-BOM" else "latin1")
  }
  if (!is.data.frame(df) || ncol(df) < 2 || nrow(df) < 1) stop("The file needs a header row, at least two columns and one data row.")
  df[] <- lapply(df, as.character)
  names(df) <- trimws(names(df))
  if (anyDuplicated(names(df)) || any(!nzchar(names(df)))) stop("Column names must be non-empty and unique.")
  df
}
