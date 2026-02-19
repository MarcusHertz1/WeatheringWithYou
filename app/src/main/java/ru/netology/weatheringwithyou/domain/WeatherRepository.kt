package ru.netology.weatheringwithyou.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    val weatherApi: WeatherApiService
) {
    suspend fun getWeather(city: String): WeatherData {
        return try {
            val response = weatherApi.getWeather(city)
            response.toData()
        } catch (e: Exception) {
            throw e
        }
    }
}