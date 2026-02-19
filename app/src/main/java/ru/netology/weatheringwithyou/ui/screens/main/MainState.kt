package ru.netology.weatheringwithyou.ui.screens.main

import ru.netology.weatheringwithyou.domain.WeatherData
import ru.netology.weatheringwithyou.ui.appBase.UiState
import ru.netology.weatheringwithyou.utils.AppState
import ru.netology.weatheringwithyou.utils.City

data class MainState (
    val selectedCity: City = AppState().city,
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = true,
    val error: String? = null
) : UiState