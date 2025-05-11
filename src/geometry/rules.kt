package geometry

import kotlin.math.sqrt

/**
 * This function calculates the missing side of the triangle based on the Pythagoras Rule
 * You should pass all the args values except the one wanted to be calculated.
 **/
fun pythagorasRule(hypotenuse: Double? = null, a: Double? = null, b: Double? = null): Double? {
    if (!twoValuesPresent(hypotenuse, a, b)) throw Exception("You should pass exactly two values")
    return when {
        (hypotenuse == null) -> sqrt((a!! * a) + (b!! * b))
        (a == null) -> sqrt((hypotenuse * hypotenuse) - (b!! * b))
        (b == null) -> sqrt((hypotenuse * hypotenuse) - (a * a))
        else -> null
    }
}

/**
 *  Private Utils
 **/
private fun twoValuesPresent(hypotenuse: Double?, a: Double?, b: Double?): Boolean {
    return listOf(hypotenuse, a, b).count { it == null } == 1
}