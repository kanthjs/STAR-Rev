## Compatibility shims for code written for R < 4.0 (stringsAsFactors defaulted to TRUE).
## These mask the base versions inside the STAR namespace only.
data.frame <- function(..., stringsAsFactors = TRUE) base::data.frame(..., stringsAsFactors = stringsAsFactors)
read.csv <- function(..., stringsAsFactors = TRUE) utils::read.csv(..., stringsAsFactors = stringsAsFactors)
read.table <- function(..., stringsAsFactors = TRUE) utils::read.table(..., stringsAsFactors = stringsAsFactors)
read.delim <- function(..., stringsAsFactors = TRUE) utils::read.delim(..., stringsAsFactors = stringsAsFactors)
