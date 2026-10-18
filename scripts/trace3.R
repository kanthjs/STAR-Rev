suppressMessages(library(STAR)); data(Maize); mm <- Maize; mt <- mtcars
options(error=function(){traceback(3);quit(status=1)})
w <- commandArgs(TRUE)[1]
if (w=="r") LinearRegressionAnalysis(data="mt", depVar="mpg", indepVar=c("wt","hp"), confInt=TRUE, covMatrix=TRUE, VIF=TRUE)
