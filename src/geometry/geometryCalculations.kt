package geometry

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