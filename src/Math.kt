import kotlin.math.abs
import kotlin.math.ln
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
private fun convertTextItemsToArray(text: String): List<String> {
    return text.trim()
        .replace(" ", ",", true)
        .split(",")
        .filter { it != "" && it != " " }
}

/**
 * finds the greatest common divisor of two numbers
 **/
tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a
    else gcd(b, a % b)
}

/**
 * finds the least common divisor of two numbers
 **/
fun lcm(a: Int, b: Int): Int {
    return if (a == 0 || b == 0) 0 else (a * b) / gcd(a, b)
}

/**
 * finds the greatest common divisor of list of numbers
 **/
fun gcdList(vararg numbers: Int): Int {
    return numbers.reduce { a, b -> gcd(a, b) }
}

/**
 * finds the least common divisor of list of numbers
 **/
fun lcmList(vararg numbers: Int): Int {
    return numbers.reduce { a, b -> lcm(a, b) }
}

/**
 * finds the factorial of a number n * (n-1) * (n-2) ... 2 * 1
 **/
tailrec fun factorial(n: Int, acc: Long = 1): Long {
    return if (n == 0 || n == 1) acc
    else factorial(n - 1, acc * n)
}

/**
 * Checks if the number is prime, i.e., divisible only by 1 and itself.
 **/
fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

/**
 * finds the fibonacci of a number
 * fib(n) = fib(n-1) + fib(n-2)
 *  O(n) time, O(1) space and complexities
 **/
fun Int.fibonacci(n: Int): Int {
    if (n < 2) return n
    var first = 0
    var second = 1
    for (i in 2..n) {
        val temp = second
        second += first
        first = temp
    }
    return second
}

/**
 * These pair of functions will calculate the max or min in an array,
 *  but discards the chosen index
 **/
fun IntArray.maxExceptIndex(index: Int): Int {
    var max = Int.MIN_VALUE
    for (i in indices) {
        if (i != index && this[i] > max) {
            max = this[i]
        }
    }
    return max
}
fun IntArray.minExceptIndex(index: Int): Int {
    var min = Int.MAX_VALUE
    for (i in indices) {
        if (i != index && this[i] < min) {
            min = this[i]
        }
    }
    return min
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
 * will always return a valid index to access a list of items
 * it will consider the list as cycle
 **/
fun IntArray.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
fun <T> Collection<T>.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
fun <T> Array<T>.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
private fun getCyclicIndex(index: Int, size: Int): Int {
    return if (index < 0) {
        size + index
    } else {
        index % size
    }
}

/**
 * will count the number of ones and zeros in the binary representation
 * in a number
 **/
fun Int.countBinaryBits(): Pair<Int, Int> {
    var x = this
    var ones = 0
    var zeros = 0
    while (x > 0) {
        if (x % 2 == 1) ones++
        else zeros++
        x /= 2
    }
    return Pair(zeros, ones)
}

/**
 * returns the sum and count of digits of an integer number
 **/
fun Int.digitsSumAndCount(): Pair<Int, Int> {
    var num = this
    var sum = 0
    var counter = 0
    while (num > 0) {
        sum += num % 10
        counter++
        num /= 10
    }
    return Pair(sum, counter)
}

/**
 * will find the number of combinations of (n/r)
 **/
fun combinations(n: Int, r: Int): Long {
    return factorial(n) / (factorial(r) * factorial(n - r))
}

/**
 * finds the min and the max in array in one loop
 **/
fun IntArray.minMax(): Pair<Int, Int> {
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for (num in this) {
        if (num > max) max = num
        if (num < min) min = num
    }
    return Pair(min, max)
}

/**
 * calculates the slope of two points
 *  point = {x,y}
 **/
fun calculateSlope(point1: IntArray, point2: IntArray): Double? {
    if (point1.size < 2 || point2.size < 2) return null
    return ((point2[1] - point1[1]) * 1.0) / (point2[0] - point1[0])
}

/**
 * calculates the distance between two numbers in 2D
 **/
private fun euclideanDistance(x: Int, y: Int, queryX: Int, queryY: Int): Int {
    return (x - queryX) * (x - queryX) + (y - queryY) * (y - queryY)
}

/**
 * calculates the log of custom base
 **/
fun log(x: Int, base: Int): Int {
    return (ln(x.toDouble()) / ln(base.toDouble())).toInt()
}
/**
 * calculates the log of base 2
 **/
fun log2(n: Int): Int {
    return log(n, 2)
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
fun Int.toBoolean() = abs(this) >= 1
infix fun Double.p(power: Double) = Math.pow(this, power)

const val ERROR_CODE = -1

