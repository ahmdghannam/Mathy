package conversions

/**
 * Energy Conversions
 **/
fun Double.joulesToCalories() = this / 4.184
fun Double.joulesToKiloCalories() = this / 4184
fun Double.caloriesToJoules() = this * 4.184
fun Double.kiloCaloriesToJoules() = this * 4184
fun Double.kWhToJoules() = this * 3_600_000
fun Double.joulesToKWh() = this / 3_600_000