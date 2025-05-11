package conversions

/**
 * Temperatures conversion
 **/
fun Double.celsiusToFahrenheit() = (this * (9.0 / 5)) + 32
fun Double.fahrenheitToCelsius() = (this - 32) * (5.0 / 9)
fun Double.celsiusToKelvin() = this + 273.15
fun Double.kelvinToCelsius() = this - 273.15
fun Double.fahrenheitToKelvin() = fahrenheitToCelsius().celsiusToKelvin()
fun Double.kelvinToFahrenheit() = kelvinToCelsius().celsiusToFahrenheit()