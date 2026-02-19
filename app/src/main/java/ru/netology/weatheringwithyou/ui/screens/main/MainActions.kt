package ru.netology.weatheringwithyou.ui.screens.main

import ru.netology.weatheringwithyou.ui.appBase.UiAction

sealed interface MainActions : UiAction {
    data object LoadWeather : MainActions
}