package ru.netology.weatheringwithyou.ui.screens.settings

import ru.netology.weatheringwithyou.ui.appBase.UiAction
import ru.netology.weatheringwithyou.utils.City
import ru.netology.weatheringwithyou.utils.Language
import ru.netology.weatheringwithyou.utils.Theme

sealed interface SettingsActions : UiAction {
    data class updateTheme(val theme: Theme) : SettingsActions
    data class udpateCity(val city: City) : SettingsActions
    data class updateLanguage(val language: Language) : SettingsActions
}