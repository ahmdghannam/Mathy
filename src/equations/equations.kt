package equations

import kotlin.math.sqrt

/**
 * Solves the quadratic equation of the form: ax² + bx + c = 0
 *
 * Uses the quadratic formula to compute the two possible real solutions:
 *      x = (-b ± √(b² - 4ac)) / (2a)
 *
 * @param a The coefficient of x² (must not be zero).
 * @param b The coefficient of x.
 * @param c The constant term.
 * @return A [Pair] of [Double] values representing the two real solutions.
 *         The first value is the solution with the '+' sign, and the second is with the '−' sign.
 * @throws IllegalArgumentException If 'a' is zero or the discriminant is negative (i.e., no real roots).
 */
fun solveQuadraticEquation(a: Double, b: Double, c: Double): Pair<Double, Double> {
    val discriminant = computeDiscriminant(a, b, c)
    val sqrtOfDiscriminant = sqrt(discriminant)
    val firstSolution = (-b + sqrtOfDiscriminant) / (2 * a)
    val secondSolution = (-b - sqrtOfDiscriminant) / (2 * a)
    return Pair(firstSolution, secondSolution)
}

/**
 * Computes the discriminant of a quadratic equation: ax² + bx + c = 0
 *
 * The discriminant is calculated as:
 *      D = b² - 4ac
 *
 * This value determines the nature of the roots:
 * - If D > 0, the equation has two distinct real roots.
 * - If D == 0, the equation has one real root (a repeated root).
 * - If D < 0, the equation has no real roots (complex roots).
 *
 * @param a The coefficient of x².
 * @param b The coefficient of x.
 * @param c The constant term.
 * @return The discriminant value as a [Double].
 */
fun computeDiscriminant(a: Double, b: Double, c: Double) = (b * b) - (4.0 * a * c)