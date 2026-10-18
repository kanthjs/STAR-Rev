`BoxsMTest.default` <-
function (X, vars, factorVar, alpha = 0.05) 
{
    x <- na.omit(X)
    if (alpha <= 0 || alpha >= 1) 
        stop("significance level must be between 0 and 1")
    X[, factorVar] = factor(X[, factorVar])
    g = nlevels(X[, factorVar])
    n = table(X[, factorVar])
    N = nrow(X)
    p = length(vars)
    bandera = 2
    if (any(n >= 20)) 
        bandera = 1
    covMat = array(dim = c(p, p, g))
    for (i in 1:g) {
        covMat[, , i] = cov(X[X[, factorVar] == levels(X[, factorVar])[i], 
            vars])
    }
    deno = sum(n) - g
    suma = array(0, dim = dim(covMat[, , 1]))
    for (k in 1:g) suma = suma + (n[k] - 1) * covMat[, , k]
    Sp = suma/deno
    Falta = 0
    for (k in 1:g) Falta = Falta + ((n[k] - 1) * log(det(covMat[, 
        , k])))
    MB = (sum(n) - g) * log(det(Sp)) - Falta
    suma1 = sum(1/(n[1:g] - 1))
    suma2 = sum(1/((n[1:g] - 1)^2))
    C = (((2 * p^2) + (3 * p) - 1)/(6 * (p + 1) * (g - 1))) * 
        (suma1 - (1/deno))
    if (bandera == 1) {
        X2 = MB * (1 - C)
        v = as.integer((p * (p + 1) * (g - 1))/2)
        P = pchisq(X2, v, lower.tail = TRUE)
        return(list(MBox = MB, ChiSq = X2, df = v, pValue = P))
    }
    else {
        Co = (((p - 1) * (p + 2))/(6 * (g - 1))) * (suma2 - (1/(deno^2)))
        if (Co - (C^2) >= 0) {
            v1 = as.integer((p * (p + 1) * (g - 1))/2)
            v21 = as.integer(trunc((v1 + 2)/(Co - (C^2))))
            F1 = MB * ((1 - C - (v1/v21))/v1)
            P1 = pf(F1, v1, v21, lower.tail = FALSE)
            return(list(MBox = MB, F = F1, df1 = v1, df2 = v21, 
                pValue = P1))
        }
        else {
            v1 = as.integer((p * (p + 1) * (g - 1))/2)
            v22 = as.integer(trunc((v1 + 2)/((C^2) - Co)))
            b = v22/(1 - C - (2/v22))
            F2 = (v22 * MB)/(v1 * (b - MB))
            P2 = pf(F2, v1, v22, lower.tail = FALSE)
            return(list(MBox = MB, F = F2, df1 = v1, df2 = v22, 
                pValue = P2))
        }
    }
}
