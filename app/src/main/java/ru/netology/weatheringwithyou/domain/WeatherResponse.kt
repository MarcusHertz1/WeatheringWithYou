package ru.netology.weatheringwithyou.domain

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import ru.netology.weatheringwithyou.utils.getWeatherByDescriptionFromServer
import java.util.Date
import java.util.Locale

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val sys: Sys,
    val name: String // название города из ответа
)

data class Weather(
    val description: String
)

 data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

 data class Wind(
    val speed: Double
)

 data class Sys(
    val sunrise: Long,
    val sunset: Long
)

fun WeatherResponse.toData() = WeatherData(
    weather = getWeatherByDescriptionFromServer(weather.firstOrNull()?.description.orEmpty()),
    temperature = main.temp.toInt(),
    feelsLike = main.feels_like.toInt(),
    temperatureMin = main.temp_min.toInt(),
    temperatureMax = main.temp_max.toInt(),
    pressure = main.pressure,
    humidity = main.humidity,
    visibility = visibility,
    windSpeed = wind.speed,
    sunrise = sys.sunrise.formatTimestampToHHmm(),
    sunset = sys.sunset.formatTimestampToHHmm()
)

private fun Long.formatTimestampToHHmm(timeZoneId: String = "Europe/Moscow"): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone(timeZoneId)
    return sdf.format(Date(this * 1000L))
}