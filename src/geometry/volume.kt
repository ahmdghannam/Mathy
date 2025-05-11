package geometry

/**
 * These functions will calculate the Volumes of different shapes
 **/
fun cubeVolume(edge: Double) = edge * edge * edge
fun cuboidVolume(length: Double, width: Double, height: Double) = length * width * height
fun coneVolume(radius: Double, height: Double) = (1 / 3) * Math.PI * radius * radius * height
fun cylinderVolume(radius: Double, height: Double) = Math.PI * radius * radius * height
fun sphereVolume(radius: Double) = (4 / 3) * Math.PI * radius * radius * radius
fun pyramidVolume(baseSide: Double, height: Double) = (1 / 3) * baseSide * baseSide * height