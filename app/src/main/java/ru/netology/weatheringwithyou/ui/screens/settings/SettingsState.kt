package ru.netology.weatheringwithyou.ui.screens.settings

import ru.netology.weatheringwithyou.ui.appBase.UiState
import ru.netology.weatheringwithyou.utils.AppState
import ru.netology.weatheringwithyou.utils.City
import ru.netology.weatheringwithyou.utils.Language
import ru.netology.weatheringwithyou.utils.Theme

data class SettingsState(
    val city: City = AppState().city,
    val language: Language = AppState().language,
    val theme: Theme = AppState().theme
) : UiState