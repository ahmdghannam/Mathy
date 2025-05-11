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

/**
 * this function will return the sum of all numbers from 1 to N
 **/
fun getSumFromOneToN(N: Int): Int {
    return ((N) * (N + 1)) / 2
}

fun Int.getSumOfSquaresFromOneToN(): Int {
    return (this * (this + 1) * (2 * this + 1)) / 6
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
fun euclideanDistance(x: Int, y: Int, queryX: Int, queryY: Int): Int {
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
 * These functions will calculate the Areas of different shapes
 **/
fun rectangleArea(width: Double, height: Double) = width * height
fun squareArea(side: Double) = side * side
fun circleArea(radius: Double) = Math.PI * radius * radius
fun triangleArea(base: Double, height: Double) = 0.5 * base * height
fun ellipseArea(a: Double, b: Double) = Math.PI * a * b
fun trapezoidArea(height: Double, base1: Double, base2: Double) = height * (base1 + base2) / 2
fun parallelogramArea(height: Double, base: Double) = height * base

/**
 * These functions will calculate the Perimeters of different shapes
 **/
fun circlePerimeter(radius: Double) = 2 * Math.PI * radius
fun trianglePerimeter(a: Double, b: Double, c: Double) = a + b + c
fun rectanglePerimeter(width: Double, height: Double) = 2 * (width + height)
fun squarePerimeter(side: Double) = 4 * side
fun trapezoidPerimeter(a: Double, b: Double, c: Double, d: Double) = a + b + c + d

/**
 * These functions will calculate the Volumes of different shapes
 **/
fun cubeVolume(edge: Double) = edge * edge * edge
fun cuboidVolume(length: Double, width: Double, height: Double) = length * width * height
fun coneVolume(radius: Double, height: Double) = (1 / 3) * Math.PI * radius * radius * height
fun cylinderVolume(radius: Double, height: Double) = Math.PI * radius * radius * height
fun sphereVolume(radius: Double) = (4 / 3) * Math.PI * radius * radius * radius
fun pyramidVolume(baseSide: Double, height: Double) = (1 / 3) * baseSide * baseSide * height

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
 * Temperatures conversion
 **/
fun Double.celsiusToFahrenheit() = (this * (9.0 / 5)) + 32
fun Double.fahrenheitToCelsius() = (this - 32) * (5.0 / 9)
fun Double.celsiusToKelvin() = this + 273.15
fun Double.kelvinToCelsius() = this - 273.15
fun Double.fahrenheitToKelvin() = fahrenheitToCelsius().celsiusToKelvin()
fun Double.kelvinToFahrenheit() = kelvinToCelsius().celsiusToFahrenheit()

/**
 * length conversion
 **/
fun Double.inchesToCentimeters() = this * 2.54
fun Double.centimetersToInchesTo() = this / 2.54
fun Double.feetToMeters() = this / 3.281
fun Double.metersToFeet() = this * 3.281
fun Double.milesToKilometers() = this * 1.60934
fun Double.kilometersToMiles() = this / 1.60934

/**
 * Weight and Mass Conversions
 **/
fun Double.kilogramsToPounds() = this * 2.20462
fun Double.poundsToKilograms() = this / 2.20462
fun Double.gramsToOunces() = this / 28.3495
fun Double.ouncesToGramsTo() = this * 28.3495

/**
 * Time Conversions
 **/
fun Double.minutesToSeconds() = this * 60
fun Double.secondsToMinutes() = this / 60
fun Double.hoursToMinutes() = this * 60
fun Double.minutesToHoursTo() = this / 60
fun Double.daysToHours() = this * 24
fun Double.hoursToDays() = this / 24

/**
 * Speed Conversions
 **/
fun Double.kMphToMph() = this / 1.60934
fun Double.mphTokMph() = this * 1.60934

/**
 * Energy Conversions
 **/
fun Double.joulesToCalories() = this / 4.184
fun Double.joulesToKiloCalories() = this / 4184
fun Double.caloriesToJoules() = this * 4.184
fun Double.kiloCaloriesToJoules() = this * 4184
fun Double.kWhToJoules() = this * 3_600_000
fun Double.joulesToKWh() = this / 3_600_000

/**
some handy extension compact functions
 **/
fun Int.isDivisibleBy(num: Int) = this % num == 0
fun Int.isPowerOfTwo() = this > 0 && (this and (this - 1)) == 0
fun haveOppositeSigns(a: Int, b: Int) = (a xor b) < 0
fun Int.noNegative() = if (this < 0) 0 else this
fun Int.noPositive() = if (this > 0) 0 else this
fun Boolean.toInt() = if (this) 1 else 0
fun Int.toBoolean() = this >= 1
fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 == 1
infix fun Double.p(power: Double) = Math.pow(this, power)

const val ERROR_CODE = -1


// private utils
private fun twoValuesPresent(hypotenuse: Double?, a: Double?, b: Double?): Boolean {
    return listOf(hypotenuse, a, b).count { it == null } == 1
}

private fun getCyclicIndex(index: Int, size: Int): Int {
    return if (index < 0) {
        size + index
    } else {
        index % size
    }
}

private fun convertTextItemsToArray(text: String): List<String> {
    return text.trim()
        .replace(" ", ",", true)
        .split(",")
        .filter { it != "" && it != " " }
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