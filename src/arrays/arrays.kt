package arrays

import numbers.gcd
import numbers.lcm
import kotlin.math.abs

/**
 * will always return a valid index to access a list of items
 * it will consider the list as cycle
 * arr[-1] -> last item
 * arr[-size - 1] -> last item
 * arr[size] -> first item
 * @return valid integer index
 **/
fun IntArray.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
fun <T> Collection<T>.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
fun <T> Array<T>.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)

/**
 * Returns the element at the given [index], wrapping around the array if the index is out of bounds.
 *
 * The index is normalized using [getCyclicIndex], so both negative and overly large indices
 * are wrapped cyclically (i.e., like modulo operation).
 *
 * For example, `index = -1` returns the last element, and `index = size` returns the first element.
 *
 * @receiver the array from which to retrieve the element.
 * @param index the index to access, which can be any integer (positive or negative).
 * @return the element at the cyclically normalized index.
 */
fun IntArray.getElementAtCyclicIndex(index: Int): Int = getCyclicIndex(index, size).let { this[it] }
fun <T> List<T>.getElementAtCyclicIndex(index: Int): T = getCyclicIndex(index, size).let { this[it] }
fun <T> Array<T>.getElementAtCyclicIndex(index: Int): T = getCyclicIndex(index, size).let { this[it] }

/**
 * Returns the element at the given [index] or `null` if the index is out of bounds.
 *
 * Uses a safe index lookup via [getIndexOrNull], so negative indices or indices ≥ size
 * will yield `null`.
 *
 * @receiver the array from which to retrieve the element.
 * @param index the position of the element to retrieve.
 * @return the element at `index`, or `null` if `index` is out of the array’s bounds.
 */
fun IntArray.getOrNull(index: Int): Int? = getIndexOrNull(index, size)?.let { this[it] }
fun <T> List<T>.getOrNull(index: Int): T? = getIndexOrNull(index, size)?.let { this[it] }
fun <T> Array<T>.getOrNull(index: Int): T? = getIndexOrNull(index, size)?.let { this[it] }

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
 *  Private Utils
 **/
private fun getCyclicIndex(index: Int, size: Int): Int {
    return if (index < 0) {
        (size - (abs(index) % (size))) % size
    } else {
        index % size
    }
}

private fun getIndexOrNull(index: Int, size: Int): Int? {
    return if (index in 0..<size) {
        index
    } else {
        null
    }
}
