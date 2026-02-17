package ru.netology.weatheringwithyou.ui.screens.settings

import ru.netology.weatheringwithyou.ui.appBase.UiAction
import ru.netology.weatheringwithyou.utils.City
import ru.netology.weatheringwithyou.utils.Language
import ru.netology.weatheringwithyou.utils.Theme

sealed interface SettingsActions : UiAction {
    data class UpdateTheme(val theme: Theme) : SettingsActions
    data class UpdateCity(val city: City) : SettingsActions
    data class UpdateLanguage(val language: Language) : SettingsActions
}