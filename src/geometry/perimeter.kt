package geometry

/**
 * These functions will calculate the Perimeters of different shapes
 **/
fun circlePerimeter(radius: Double) = 2 * Math.PI * radius
fun trianglePerimeter(a: Double, b: Double, c: Double) = a + b + c
fun rectanglePerimeter(width: Double, height: Double) = 2 * (width + height)
fun squarePerimeter(side: Double) = 4 * side
fun trapezoidPerimeter(a: Double, b: Double, c: Double, d: Double) = a + b + c + d