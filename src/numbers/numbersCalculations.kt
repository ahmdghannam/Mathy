package numbers

import kotlin.math.ln

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
 * finds the factorial of a number n * (n-1) * (n-2) ... 2 * 1
 **/
tailrec fun factorial(n: Int, acc: Long = 1): Long {
    return if (n == 0 || n == 1) acc
    else factorial(n - 1, acc * n)
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

