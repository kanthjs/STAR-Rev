# Results in other forms: a summary in Thai, R code that reproduces an analysis outside the app, and a report that
# opens in Microsoft Word. Sourced by app.R (needs fmt_n, fmt_p, transforms, is_split, is_comb, precision_of).

# ---- summary in Thai ---------------------------------------------------------------------------------------------
th_p <- function(p) if (is.na(p)) "p = NA" else if (p < 0.0001) "p < 0.0001" else paste("p =", formatC(p, format = "f", digits = 4))
th_f <- function(r, f) { e <- r$effects[r$effects$Effect == f, ]; sprintf("F = %s, %s", fmt_n(e$F), th_p(e$P)) }
th_test <- c(LSD = "LSD", HSD = "Tukey HSD", duncan = "Duncan (DMRT)", SNK = "SNK", scheffe = "Scheffe", bonferroni = "Bonferroni")
th_best <- function(m) sprintf("%s (%s)", m$Treatment[which.max(m$Mean)], fmt_n(max(m$Mean)))

thai_summary_aov <- function(r, np = NULL) {
  y <- r$original_response %||% r$response
  fs <- r$factors
  out <- character()
  if (!is.null(r$transform)) out <- c(out, sprintf("วิเคราะห์ข้อมูล %s ที่แปลงด้วย %s ค่าเฉลี่ยและการเปรียบเทียบอยู่ในสเกลที่แปลงแล้ว", y, transforms[[r$transform]]$label))
  main_sentence <- function(f) {
    e <- r$effects[r$effects$Effect == f, ]
    if (isTRUE(e$Significant))
      sprintf("%s มีผลต่อ %s อย่างมีนัยสำคัญทางสถิติ (%s) ค่าเฉลี่ยสูงสุดคือ %s ค่าเฉลี่ยที่มีอักษรเหมือนกันไม่แตกต่างกันทางสถิติ (เปรียบเทียบด้วยวิธี %s)",
              f, y, th_f(r, f), th_best(r$means[[f]]), th_test[[r$tests_used[[f]]]] %||% r$tests_used[[f]])
    else sprintf("%s ไม่มีผลต่อ %s อย่างมีนัยสำคัญทางสถิติ (%s)", f, y, th_f(r, f))
  }
  if (length(fs) == 1) {
    out <- c(out, main_sentence(fs))
  } else {
    inter <- paste(fs, collapse = ":")
    if (isTRUE(r$interaction_significant)) {
      out <- c(out, sprintf("ปฏิสัมพันธ์ระหว่าง %s และ %s มีนัยสำคัญทางสถิติ (%s) แสดงว่าผลของปัจจัยหนึ่งขึ้นอยู่กับระดับของอีกปัจจัยหนึ่ง จึงเปรียบเทียบค่าเฉลี่ยภายในแต่ละระดับ (simple effects) แทนค่าเฉลี่ยรวม",
                            fs[1], fs[2], th_f(r, inter)))
      cl <- r$cells[which.max(r$cells$Mean), ]
      out <- c(out, sprintf("ค่าเฉลี่ยสูงสุดคือ %s = %s และ %s = %s (%s)", fs[1], cl[[fs[1]]], fs[2], cl[[fs[2]]], fmt_n(cl$Mean)))
    } else {
      out <- c(out, sprintf("ปฏิสัมพันธ์ระหว่าง %s และ %s ไม่มีนัยสำคัญทางสถิติ (%s)", fs[1], fs[2], th_f(r, inter)), vapply(fs, main_sentence, ""))
    }
  }
  out <- c(out, if (is.null(r$cv_a)) sprintf("ค่าสัมประสิทธิ์ความแปรปรวน (CV) = %.1f%%", r$cv)
                else sprintf("ค่าสัมประสิทธิ์ความแปรปรวน CV(a) = %.1f%% และ CV(b) = %.1f%%", r$cv_a, r$cv_b))
  if (is_comb(r$design) && !is.na(r$bartlett$p)) {
    out <- c(out, if (r$bartlett$p >= r$alpha) sprintf("ความแปรปรวนของความคลาดเคลื่อนในแต่ละ %s ไม่แตกต่างกัน (Bartlett, %s) จึงรวมวิเคราะห์ได้", r$env, th_p(r$bartlett$p))
                  else sprintf("ความแปรปรวนของความคลาดเคลื่อนในแต่ละ %s แตกต่างกัน (Bartlett, %s) การรวมวิเคราะห์อาจไม่เหมาะสม ควรรายงานผลแยกแต่ละ %s หรือแปลงข้อมูล", r$env, th_p(r$bartlett$p), r$env))
  }
  dg <- r$diagnostics
  if (!is.null(dg) && !isTRUE(dg$ok)) {
    out <- c(out, paste0("ข้อมูลไม่เป็นไปตามข้อตกลงเบื้องต้นของการวิเคราะห์ความแปรปรวนทั้งหมด (ดูแท็บ Outliers & assumptions)",
                         if (!is.null(np)) sprintf(" การทดสอบแบบไม่อิงพารามิเตอร์ (%s) ให้ %s", np$name, th_p(np$p)) else ""))
  }
  paste0(paste(out, collapse = " "), "")
}

thai_summary_tt <- function(r) {
  tr <- r$test[r$chosen, ]
  sig <- r$significant
  ci <- sprintf("ช่วงความเชื่อมั่น %g%% ของผลต่าง %s ถึง %s", 100 * r$conf_level, fmt_n(tr$Lower), fmt_n(tr$Upper))
  st <- sprintf("t = %.2f, df = %s, %s", tr$t, .star_tt_df(tr$DF), th_p(tr$P))
  main <- switch(r$type,
    independent = sprintf("ค่าเฉลี่ย %s ของ %s (%s) %sกับ %s (%s) ผลต่าง = %s (%s; %s)", r$response, r$labels[1], fmt_n(r$groups$Mean[1]),
                          if (sig) "แตกต่างอย่างมีนัยสำคัญทางสถิติ" else "ไม่แตกต่างอย่างมีนัยสำคัญทางสถิติ", r$labels[2], fmt_n(r$groups$Mean[2]), fmt_n(tr$Difference), st, ci),
    paired = sprintf("ค่าเฉลี่ยของ %s (%s) และ %s (%s) %s ผลต่างเฉลี่ย = %s (%s; %s)", r$x, fmt_n(r$groups$Mean[1]), r$y, fmt_n(r$groups$Mean[2]),
                     if (sig) "แตกต่างกันอย่างมีนัยสำคัญทางสถิติ" else "ไม่แตกต่างกันอย่างมีนัยสำคัญทางสถิติ", fmt_n(tr$Difference), st, ci),
    one = sprintf("ค่าเฉลี่ย %s (%s) %sจากค่า %s (%s; %s)", r$response, fmt_n(r$groups$Mean[1]),
                  if (sig) "แตกต่างอย่างมีนัยสำคัญทางสถิติ" else "ไม่แตกต่างอย่างมีนัยสำคัญทางสถิติ", format(r$mu), st, ci))
  size <- c(negligible = "น้อยมาก", small = "เล็ก", medium = "ปานกลาง", large = "ใหญ่")[[.star_tt_effect_word(r$effect_size)]]
  paste0(main, sprintf(" ขนาดอิทธิพล Cohen's d = %.2f (%s)", r$effect_size, size))
}

# ---- R code that reproduces an analysis -------------------------------------------------------------------------
rq <- function(x) if (identical(make.names(x), x)) x else paste0("`", x, "`")      # name in a formula
rd <- function(x) paste0("d$", rq(x))                                              # column of d
r_num <- function(x) format(x, digits = 10, scientific = FALSE, trim = TRUE)
r_test_fun <- c(LSD = "LSD.test", HSD = "HSD.test", duncan = "duncan.test", SNK = "SNK.test", scheffe = "scheffe.test", bonferroni = "LSD.test")
r_test_call <- function(test, y, tr, df, ms, alpha) {
  extra <- if (identical(test, "bonferroni")) ', p.adj = "bonferroni"' else ""
  sprintf("%s(%s, %s, DFerror = %s, MSerror = %s, alpha = %s%s)$groups", r_test_fun[[test]], y, tr, r_num(df), r_num(ms), alpha, extra)
}

r_code_aov <- function(r, file_name = NULL) {
  y <- r$response; y0 <- r$original_response %||% y
  fs <- r$factors; blk <- r$block
  head <- c(sprintf("# R code that reproduces this analysis (%s %s). It uses base R and the agricolae package.", app_name, version_label()),
            "# install.packages(\"agricolae\")   # once",
            "library(agricolae)",
            sprintf("d <- %s", if (!is.null(file_name) && grepl("[.]xlsx$", file_name, ignore.case = TRUE)) sprintf("as.data.frame(readxl::read_excel(\"%s\"))", file_name)
                               else sprintf("read.csv(\"%s\")", file_name %||% "your_data.csv")),
            sprintf("%s <- factor(%s)", rd(c(fs, blk)), rd(c(fs, blk))))
  if (!is.null(r$transform)) {
    expr <- switch(r$transform, log = sprintf("log(%s + 1)", rd(y0)), sqrt = sprintf("sqrt(%s + 0.5)", rd(y0)),
                   arcsin = sprintf("asin(sqrt(%s / 100)) * 180 / pi   # degrees", rd(y0)))
    head <- c(head, sprintf("%s <- %s", rd(y), expr))
  }
  q <- rq
  rhs <- switch(r$design,
    CRD = paste(q(fs), collapse = " * "),
    RCBD = paste(q(blk), "+", paste(q(fs), collapse = " * ")),
    SplitRCBD = sprintf("%s + %s * %s + Error(%s:%s)", q(blk), q(r$main), q(r$sub), q(blk), q(r$main)),
    SplitCRD = sprintf("%s * %s + Error(%s:%s)", q(r$main), q(r$sub), q(blk), q(r$main)),
    CombRCBD = sprintf("%s + %s:%s + %s + %s:%s", q(r$env), q(r$env), q(blk), q(r$treatment), q(r$treatment), q(r$env)),
    CombCRD = sprintf("%s + %s + %s:%s", q(r$env), q(r$treatment), q(r$treatment), q(r$env)))
  fit <- c(sprintf("fit <- aov(%s ~ %s, data = d)%s", q(y), rhs, if (is_split(r$design) && !is.null(blk) && identical(r$design, "SplitRCBD")) "   # R warns that the Error() model is singular: expected here" else ""), "summary(fit)")
  if (r$design %in% c("CRD", "RCBD")) fit <- c(fit, "cv.model(fit)                       # CV (%)", "shapiro.test(residuals(fit))        # normality of the residuals")
  if (identical(r$design, "CombRCBD")) {
    a <- r$anova
    fit <- c(fit, sprintf("# %s is tested against %s within %s: F = MS(%s) / MS(%s:%s) = %s / %s", r$env, blk, r$env, r$env, r$env, blk,
                          r_num(a$MS[1]), r_num(a$MS[2])),
             sprintf("pf(%s / %s, %d, %d, lower.tail = FALSE)", r_num(a$MS[1]), r_num(a$MS[2]), a$DF[1], a$DF[2]))
  }
  cmp <- "# mean comparison (error df and mean square from the ANOVA table above)"
  if (isTRUE(r$interaction_significant) && length(r$simple)) {
    for (nm in names(r$simple)) {
      sm <- r$simple[[nm]]; cf <- attr(sm, "compare"); af <- attr(sm, "at")
      # simple effects use the pooled error; in a split plot the sub plot within a main plot uses Error(b)
      e <- if (!is_split(r$design)) r$anova[r$anova$Source == "Error", ] else if (identical(cf, r$sub)) error_of(r, r$sub)
      if (is.null(e)) {
        cmp <- c(cmp, sprintf("# %s: %s is compared with the pooled error of Error(a) and Error(b) (Satterthwaite df), see the app's report", nm, cf))
        next
      }
      cmp <- c(cmp, sprintf("# %s", nm),
               sprintf("for (lv in levels(%s)) { cat(\"%s =\", lv, \"\\n\"); i <- %s == lv; print(%s) }", rd(af), af, rd(af),
                       r_test_call(attr(sm, "test"), paste0(rd(y), "[i]"), paste0("droplevels(", rd(cf), "[i])"), e$DF, e$MS, r$alpha)))
    }
  } else {
    for (f in fs) {
      e <- error_of(r, f)
      cmp <- c(cmp, if (isTRUE(r$effects$Significant[r$effects$Effect == f])) r_test_call(r$tests_used[[f]], rd(y), rd(f), e$DF, e$MS, r$alpha)
                    else sprintf("# %s: F test not significant, so its means are not compared", f))
    }
  }
  if (r$design %in% c("CRD", "RCBD") && length(fs) == 1) {
    cmp <- c(cmp, "# rank-based alternative",
             if (is.null(blk)) sprintf("kruskal(%s, %s, alpha = %s)$groups", rd(y), rd(fs), r$alpha)
             else sprintf("friedman(%s, %s, %s, alpha = %s)$groups", rd(blk), rd(fs), rd(y), r$alpha))
  }
  paste(c(head, "", fit, "", cmp), collapse = "\n")
}

r_code_tt <- function(r, file_name = NULL) {
  alt <- if (r$alternative != "two.sided") sprintf(", alternative = \"%s\"", r$alternative) else ""
  conf <- sprintf(", conf.level = %s", r$conf_level)
  read <- sprintf("d <- %s", if (!is.null(file_name) && grepl("[.]xlsx$", file_name, ignore.case = TRUE)) sprintf("as.data.frame(readxl::read_excel(\"%s\"))", file_name)
                             else sprintf("read.csv(\"%s\")", file_name %||% "your_data.csv"))
  body <- switch(r$type,
    independent = c(sprintf("a <- %s[%s == \"%s\"]", rd(r$response), rd(r$group), r$labels[1]),
                    sprintf("b <- %s[%s == \"%s\"]", rd(r$response), rd(r$group), r$labels[2]),
                    sprintf("t.test(a, b, var.equal = %s%s%s)", if (r$var_equal) "TRUE" else "FALSE", alt, conf),
                    "var.test(a, b)                       # F test for equal variances",
                    "shapiro.test(a); shapiro.test(b)     # normality of each group",
                    sprintf("wilcox.test(a, b, conf.int = TRUE%s%s)   # rank-based alternative", alt, conf)),
    paired = c(sprintf("t.test(%s, %s, paired = TRUE%s%s)", rd(r$x), rd(r$y), alt, conf),
               sprintf("shapiro.test(%s - %s)", rd(r$x), rd(r$y)),
               sprintf("wilcox.test(%s, %s, paired = TRUE, conf.int = TRUE%s%s)   # rank-based alternative", rd(r$x), rd(r$y), alt, conf)),
    one = c(sprintf("t.test(%s, mu = %s%s%s)", rd(r$response), format(r$mu), alt, conf),
            sprintf("shapiro.test(%s)", rd(r$response)),
            sprintf("wilcox.test(%s, mu = %s, conf.int = TRUE%s%s)   # rank-based alternative", rd(r$response), format(r$mu), alt, conf)))
  paste(c(sprintf("# R code that reproduces this t-test (%s %s), base R only.", app_name, version_label()), read, "", body), collapse = "\n")
}

# ---- report for Microsoft Word ------------------------------------------------------------------------------------
# Word opens a single-file web page (MHTML) saved as .doc, with the tables as Word tables and the plot embedded; this
# needs no extra R package, so it also works when the app runs in the browser.
word_table <- function(df) {
  if (is.null(df) || !nrow(df)) return("")
  esc <- function(x) htmltools::htmlEscape(as.character(x))
  num <- vapply(df, function(v) { v <- as.character(v); v <- v[nzchar(v)]; length(v) > 0 && all(grepl("^[-<]?[0-9][0-9,.]*[*a-z ]*$", v)) }, NA)
  num[1] <- FALSE
  th <- paste0("<th", ifelse(num, " class=n", ""), ">", esc(names(df)), "</th>", collapse = "")
  rows <- vapply(seq_len(nrow(df)), function(i) paste0("<tr>", paste0("<td", ifelse(num, " class=n", ""), ">", esc(unlist(df[i, ], use.names = FALSE)), "</td>", collapse = ""), "</tr>"), "")
  paste0("<table><tr>", th, "</tr>", paste(rows, collapse = ""), "</table>")
}
word_doc <- function(file, title, blocks, png = NULL) {
  css <- paste("body { font-family: 'TH Sarabun New', 'TH SarabunPSK', 'Cordia New', sans-serif; font-size: 16pt; }",
               "h1 { font-size: 22pt; } h2 { font-size: 18pt; margin-top: 14pt; } p.small { font-size: 13pt; color: #555; }",
               "table { border-collapse: collapse; margin: 4pt 0 8pt; } th, td { border-top: 1px solid #999; border-bottom: 1px solid #999; padding: 1pt 6pt; font-size: 14pt; }",
               "th { text-align: left; } .n { text-align: right; } pre { font-family: Consolas, monospace; font-size: 10pt; }")
  body <- paste(c(sprintf("<h1>%s</h1>", htmltools::htmlEscape(title)), unlist(blocks),
                  if (!is.null(png)) "<h2>Plot</h2><p><img width=600 src=\"file:///C:/rdstat/plot.png\"></p>"), collapse = "\n")
  html <- sprintf("<html xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\"><head><meta charset=\"utf-8\"><style>%s</style></head><body lang=th>%s</body></html>", css, body)
  b <- "----=_NextPart_RDstat"
  parts <- c("MIME-Version: 1.0", sprintf("Content-Type: multipart/related; boundary=\"%s\"", b), "",
             paste0("--", b), "Content-Type: text/html; charset=\"utf-8\"", "Content-Transfer-Encoding: base64",
             "Content-Location: file:///C:/rdstat/report.htm", "", b64_lines(charToRaw(enc2utf8(html))))
  if (!is.null(png)) parts <- c(parts, "", paste0("--", b), "Content-Type: image/png", "Content-Transfer-Encoding: base64",
                                 "Content-Location: file:///C:/rdstat/plot.png", "", b64_lines(readBin(png, "raw", file.size(png))))
  writeLines(c(parts, "", paste0("--", b, "--")), file, useBytes = TRUE)
}
b64_lines <- function(raw) { s <- jsonlite::base64_enc(raw); s <- gsub("\\s", "", s); substring(s, seq(1, nchar(s), 76), pmin(nchar(s), seq(76, nchar(s) + 75, 76))) }
w_h2 <- function(x) sprintf("<h2>%s</h2>", htmltools::htmlEscape(x))
w_p <- function(x, cls = NULL) sprintf("<p%s>%s</p>", if (is.null(cls)) "" else sprintf(" class=%s", cls), htmltools::htmlEscape(x))
