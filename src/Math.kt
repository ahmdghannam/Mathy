import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

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
    return min(A, B) + abs((getSumFromOneToN(A) - getSumFromOneToN(B)))
}

/**
 * this function will return the sum of all numbers from 1 to N
 **/
fun getSumFromOneToN(N: Int): Int {
    return ((N) * (N + 1)) / 2
}

/**
 * returns how much progress as percentage is made between start and end value
 **/
fun Int.percentageBetween(range: IntRange): Float {
    if (this.isNegative() or range.first.isNegative() or range.last.isNegative()) return ERROR_CODE.toFloat()
    val difference = abs(range.first - range.last)
    return (this - range.first) / difference.toFloat()
}

/**
 * transform a value in specific range to other range
 * ex value x is always in 1..100, so you can convert it to value from 200..300
 * below there is two versions of this fun
 **/
fun Int.mapRanges(oldStart: Int, oldEnd: Int, newStart: Int, newEnd: Int): Int {
    if (this !in oldStart..oldEnd) return ERROR_CODE
    return (this - oldStart) * (newEnd - newStart) / (oldEnd - oldStart) + newStart
}

fun Int.mapRanges(old: IntRange, new: IntRange): Int {
    if (this !in old) return ERROR_CODE
    return (this - old.first) * (new.last - new.first) / (old.last - old.first) + new.first
}

/**
 *  discard a value if it's lower or higher than specific range,
 *  returns the default value if outside the range
 **/
fun Int.discardOutside(range: IntRange, defaultValue: Int = 0): Int {
    return if (this in range) this else defaultValue
}

/**
 * force a number to be in range,
 * if higher returns the end value in the range, else if lower returns the start
 * works for Int, Long, Float and Double
 **/
fun Number.forceInRange(start: Number, end: Number): Number {
    return when {
        this.toDouble() < start.toDouble() -> start
        this.toDouble() > end.toDouble() -> end
        else -> this
    }
}

fun Long.forceInRange(range: LongRange): Long {
    return when {
        this < range.first -> range.first
        this > range.last -> range.last
        else -> this
    }
}

/**
 * compares a value if it equals a compare value or +- error rate
 **/
fun Int.isAround(compareValue: Int, errorRate: Int): Boolean {
    return this in (compareValue - errorRate)..(compareValue + errorRate)
}

/**
 * Calculates the given percentage of the integer.
 *
 * This extension function computes the specified percentage of the caller integer
 * and returns the result as an integer. If the given percentage is negative,
 * it returns `@ERROR_CODE` to indicate an invalid input.
 */
fun Int.percentOf(percentage: Int): Int {
    if (percentage < 0) return ERROR_CODE
    return (this.toFloat() * (percentage.toFloat() / 100.0)).toInt()
}

/**
 * these two below functions returns the passed value with the given percentage of its value
 * added or subtracted from it
 **/
fun Int.incByPercentage(percentage: Int): Int {
    val percentageResult = this.percentOf(percentage)
    return if (percentageResult == ERROR_CODE) ERROR_CODE else {
        this + percentageResult
    }
}

fun Int.decByPercentage(percentage: Int): Int {
    val percentageResult = this.percentOf(percentage)
    return if (percentageResult == ERROR_CODE) ERROR_CODE else {
        this - percentageResult
    }
}

/*
*  the numbers should be separated by a comma or space and passed as string
* */
fun sumNumbersInString(text: String, evenOnly: Boolean = false, oddOnly: Boolean = false): Double {
    val textSeparatedByCommas = text.trim().replace(" ", ",", true)
    val numbers = textSeparatedByCommas.split(",")
        .filter { it != "" && it != " " }
        .map { it.toDouble() }

    return numbers.sumOf { value ->
        if (evenOnly && oddOnly){
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
fun gcd(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (y != 0) {
        val temp = y
        y = x % y
        x = temp
    }
    return x
}
fun lcm(a: Int, b: Int): Int {
    return if (a == 0 || b == 0) 0 else (a * b) / gcd(a, b)
}
fun gcdList(vararg numbers: Int): Int {
    return numbers.reduce { a, b -> gcd(a, b) }
}
fun lcmList(vararg numbers: Int): Int {
    return numbers.reduce { a, b -> lcm(a, b) }
}
tailrec fun factorial(n: Int, acc: Long = 1): Long {
    return if (n == 0 || n == 1) acc
    else factorial(n - 1, acc * n)
}
fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}




/**
some handy extension compact functions
 **/
fun Int.noNegative() = if (this < 0) 0 else this
fun Int.noPositive() = if (this > 0) 0 else this
fun Int.isNegative() = this < 0
fun Int.isNegativeOrZero() = this <= 0
fun Int.isPositive() = this > 0
fun Int.isPositiveOrZero() = this >= 0
fun Boolean.toInt() = if (this) 1 else 0
fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 == 1
fun Int.toBoolean() = this >= 1
infix fun Double.p(power: Double) = Math.pow(this, power)

const val ERROR_CODE = -1

