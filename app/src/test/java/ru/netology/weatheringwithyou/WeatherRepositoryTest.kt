package ru.netology.weatheringwithyou

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.robolectric.RobolectricTestRunner
import ru.netology.weatheringwithyou.domain.Main
import ru.netology.weatheringwithyou.domain.Sys
import ru.netology.weatheringwithyou.domain.Weather
import ru.netology.weatheringwithyou.domain.WeatherApiService
import ru.netology.weatheringwithyou.domain.WeatherRepository
import ru.netology.weatheringwithyou.domain.WeatherResponse
import ru.netology.weatheringwithyou.domain.Wind

@RunWith(RobolectricTestRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryTest {

    private val api: WeatherApiService = mock()
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        repository = WeatherRepository(api)
    }

    @Test
    fun `getWeather returns mapped data`() = runTest {
        val response = WeatherResponse(
            weather = listOf(Weather("clear sky")),
            main = Main(
                temp = 20.0,
                feels_like = 19.0,
                temp_min = 18.0,
                temp_max = 22.0,
                pressure = 1012,
                humidity = 60
            ),
            visibility = 10000,
            wind = Wind(5.5),
            sys = Sys(
                sunrise = 1700000000,
                sunset = 1700040000
            ),
            name = "London"
        )

        whenever(api.getWeather("London")).thenReturn(response)

        val result = repository.getWeather("London")

        assertEquals(20, result.temperature)
        assertEquals(19, result.feelsLike)
        assertEquals(5.5, result.windSpeed, 0.0)
        assertEquals(60, result.humidity)
    }

    @Test(expected = RuntimeException::class)
    fun `getWeather throws when api fails`() = runTest {
        whenever(api.getWeather(any())).thenThrow(RuntimeException())

        repository.getWeather("London")
    }
}