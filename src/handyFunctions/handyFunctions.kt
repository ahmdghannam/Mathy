package handyFunctions
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