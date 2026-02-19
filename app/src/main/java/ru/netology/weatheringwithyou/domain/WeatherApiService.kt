package ru.netology.weatheringwithyou.domain

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = APID
    ): WeatherResponse
}

private const val APID = "7f4d425b156742ce18610d13c02dad3f"
private const val UNITS = "metric"