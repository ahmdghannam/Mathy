package geometry

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