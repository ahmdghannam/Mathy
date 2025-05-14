package numbers

import ERROR_CODE
import kotlin.math.abs
import kotlin.math.sqrt

/**
 *  Returns the number after flipping the digits
 **/
fun Int.reverseDigits(): Int {
    var num = this
    var reversed = 0
    while (num != 0) {
        val digit = num % 10
        reversed = reversed * 10 + digit
        num /= 10
    }
    return reversed
}

/**
 *  Checks if the number has a fixed (int) square root
 **/
fun Int.isPerfectSquare(): Boolean {
    val sqrt = sqrt(this.toDouble()).toInt()
    return sqrt * sqrt == this
}

/**
 * Will spread the digits of a number as a list
 *  ex 123 -> [1,2,3]
 **/
fun Int.spreadDigits(): List<Int> {
    if (this == 0) return listOf(0)
    val mutableList = mutableListOf<Int>()
    var n = Math.abs(this)
    while (n > 0) {
        mutableList.add(n % 10)
        n /= 10
    }
    return mutableList.reversed()
}

/**
 * Checks if the number is prime, i.e., divisible only by 1 and itself.
 **/
fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    if (this == 2) return true
    if (this % 2 == 0) return false
    for (i in 2..sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) return false
    }
    return true
}

/**
 * returns how much progress as percentage is made between start and end value
 **/
fun Int.percentageBetween(range: IntRange): Float {
    if (this < 0 || range.first < 0 || range.last < 0) return ERROR_CODE.toFloat()
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