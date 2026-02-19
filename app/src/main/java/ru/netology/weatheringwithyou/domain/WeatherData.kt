package ru.netology.weatheringwithyou.domain

import ru.netology.weatheringwithyou.utils.WeatherUtils

data class WeatherData (
    val weather: WeatherUtils,
    val temperature: Int,
    val feelsLike: Int,
    val temperatureMin: Int,
    val temperatureMax: Int,
    val pressure: Int,
    val humidity: Int,
    val visibility: Int,
    val windSpeed: Double,
    val sunrise: String,
    val sunset: String
)