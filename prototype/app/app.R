# STAR web prototype: RCBD randomisation + RCBD ANOVA, running the recovered STAR R engine.
library(shiny)

# Load the recovered STAR functions (plain sources, no package install needed in webR)
for (f in sort(list.files("star", pattern = "\\.R$", full.names = TRUE))) source(f, local = FALSE)

run_quiet <- function(expr) {
  out <- character()
  val <- NULL
  err <- NULL
  out <- capture.output(val <- tryCatch(expr, error = function(e) { err <<- conditionMessage(e); NULL }))
  list(text = out, value = val, error = err)
}

ui <- navbarPage(
  "STAR (prototype): agricultural experiments in the browser",
  tabPanel("1. Randomise RCBD",
    sidebarLayout(
      sidebarPanel(
        textAreaInput("trts", "Treatments (comma separated)", "T1, T2, T3, T4", rows = 3),
        numericInput("reps", "Replications (blocks)", 3, min = 2, max = 20),
        numericInput("seed", "Random seed (blank = random)", NA),
        actionButton("go_rand", "Randomise", class = "btn-primary"),
        downloadButton("dl_book", "Download field book (CSV)")
      ),
      mainPanel(
        uiOutput("rand_msg"),
        h4("Field book"), tableOutput("book"),
        h4("Layout"), verbatimTextOutput("layout")
      )
    )
  ),
  tabPanel("2. ANOVA for RCBD",
    sidebarLayout(
      sidebarPanel(
        radioButtons("src", "Data", c("Example (rice yield, 6 N levels x 3 reps)" = "ex", "Upload CSV" = "up")),
        conditionalPanel("input.src == 'up'", fileInput("file", "CSV file", accept = ".csv")),
        uiOutput("var_pickers"),
        selectInput("pw", "Mean comparison", c("None" = "", "LSD", "Tukey", "Duncan")),
        actionButton("go_aov", "Run ANOVA", class = "btn-primary")
      ),
      mainPanel(
        uiOutput("aov_msg"),
        verbatimTextOutput("aov_out")
      )
    )
  )
)

server <- function(input, output, session) {
  rand <- eventReactive(input$go_rand, {
    trts <- trimws(strsplit(input$trts, ",")[[1]]); trts <- trts[nzchar(trts)]
    if (length(trts) < 2) return(list(error = "Enter at least two treatments."))
    if (!is.na(input$seed)) set.seed(input$seed)
    res <- run_quiet(designRCBD(generate = list(Treatment = trts), r = input$reps, display = FALSE))
    if (!is.null(res$error)) return(list(error = res$error))
    list(book = res$value$fieldbook, layout = res$value$layout)
  })
  output$rand_msg <- renderUI({ r <- rand(); if (!is.null(r$error)) div(class = "alert alert-danger", r$error) })
  output$book <- renderTable({ r <- rand(); req(is.null(r$error)); r$book })
  output$layout <- renderPrint({ r <- rand(); req(is.null(r$error)); print(r$layout[[1]], quote = FALSE) })
  output$dl_book <- downloadHandler("rcbd_fieldbook.csv", function(file) {
    r <- rand(); req(is.null(r$error)); write.csv(r$book, file, row.names = FALSE)
  })

  dat <- reactive({
    if (input$src == "ex") return(read.csv("example_rcbd.csv"))
    req(input$file); read.csv(input$file$datapath)
  })
  output$var_pickers <- renderUI({
    nm <- names(dat())
    tagList(
      selectInput("resp", "Response variable", nm, selected = tail(nm, 1)),
      selectInput("trt", "Treatment factor", nm, selected = nm[min(2, length(nm))]),
      selectInput("blk", "Block / replication", nm, selected = nm[1])
    )
  })
  aov_res <- eventReactive(input$go_aov, {
    d <- dat(); req(input$resp, input$trt, input$blk)
    if (length(unique(c(input$resp, input$trt, input$blk))) < 3) return(list(error = "Choose three different columns."))
    if (!is.numeric(d[[input$resp]])) return(list(error = "The response variable must be numeric."))
    assign("star_data", d, envir = globalenv())
    args <- list(data = "star_data", design = "RCBD", respvar = input$resp, factor1 = input$trt, rep1 = input$blk)
    if (nzchar(input$pw)) { args$pwTest <- input$pw; args$pwVar <- input$trt }
    res <- run_quiet(do.call(AOVTest, args))
    if (!is.null(res$error)) return(list(error = res$error))
    list(text = res$text)
  })
  output$aov_msg <- renderUI({ r <- aov_res(); if (!is.null(r$error)) div(class = "alert alert-danger", r$error) })
  output$aov_out <- renderText({ r <- aov_res(); req(is.null(r$error)); paste(r$text, collapse = "\n") })
}

shinyApp(ui, server)
