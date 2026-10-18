suppressMessages(library(STAR)); options(error=function(){traceback(3);quit(status=1)})
designLattice(generate=list(Trt=paste0("T",1:9)), r=2)
