package arrays

import numbers.gcd
import numbers.lcm


/**
 * will always return a valid index to access a list of items
 * it will consider the list as cycle
 **/
fun IntArray.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
fun <T> Collection<T>.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)
fun <T> Array<T>.getCyclicIndex(index: Int): Int = getCyclicIndex(index, size)

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
        size + index
    } else {
        index % size
    }
}