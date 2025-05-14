package numbers

import equations.solveQuadraticEquation
import handyFunctions.isEven
import handyFunctions.isOdd
import kotlin.math.min

/**
 * this function will return the sum of all values between two Int numbers
 * both negative and positive supported
 **/
fun getSumFromAtoB(A: Int, B: Int): Int {
    return if (A > 0 && B > 0) {
        getSumFromAToBPositive(A, B)
    } else if (A < 0 && B < 0) {
        getSumFromAToBNegative(A, B)
    } else {
        getSumFromAToBMixed(A, B)
    }
}

/**
 * this function will return the sum of all numbers from 1 to N
 **/
fun getSumFromOneToN(N: Int)= N * (N + 1) / 2
fun Int.getSumOfSquaresFromOneToN(): Int {
    return (this * (this + 1) * (2 * this + 1)) / 6
}

/**
 * Calculates the value of N such that the sum of integers from 1 to N equals the given sum.
 *
 * This function solves the inverse of the triangular number formula:
 *      sum = N * (N + 1) / 2
 * by using the quadratic formula to find N given the sum.
 *
 * @param sum The total sum from 1 to N (as a Double).
 * @return The value of N (as a Double), which may be fractional if the sum doesn't
 *         correspond to a perfect triangular number.
 */
fun getUpperBoundNForSum(sum: Double): Double {
    return solveQuadraticEquation(1.0, 1.0, -2 * sum).first
}

/**
 *  the numbers should be separated by a comma or space and passed as string
 * */
fun sumNumbersInString(text: String, evenOnly: Boolean = false, oddOnly: Boolean = false): Double {
    return convertTextItemsToArray(text).map { it.toDouble() }.sumOf { value ->
        if (evenOnly && oddOnly) {
            value
        } else if (evenOnly) {
            if (value.toInt().isEven()) value else 0.0
        } else if (oddOnly) {
            if (value.toInt().isOdd()) value else 0.0
        } else {
            value
        }
    }
}

/**
 *  Private Utils
 **/
private fun getSumFromAToBMixed(A: Int, B: Int): Int {
    if ((A > 0 && B > 0) || (A < 0 && B < 0)) throw Exception("The numbers must be Mixed!")
    val negative = if (A < 0) A else B
    val positive = if (A >= 0) A else B
    return getSumFromOneToN(positive) - getSumFromOneToN(-negative)
}

private fun getSumFromAToBNegative(A: Int, B: Int): Int {
    if (A > 0 || B > 0) throw Exception("The numbers must be Negative!")
    return -getSumFromAToBPositive(-A, -B)
}

private fun getSumFromAToBPositive(A: Int, B: Int): Int {
    if (A < 0 || B < 0) throw Exception("The numbers must be positive!")
    return min(A, B) + Math.abs((getSumFromOneToN(A) - getSumFromOneToN(B)))
}
private fun convertTextItemsToArray(text: String): List<String> {
    return text.trim()
        .replace(" ", ",", true)
        .split(",")
        .filter { it != "" && it != " " }
}