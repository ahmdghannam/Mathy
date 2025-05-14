package discreteMath

import numbers.factorial

/**
 * will find the number of combinations of (n/r)
 **/
fun combinations(n: Int, r: Int): Long {
    return factorial(n) / (factorial(r) * factorial(n - r))
}