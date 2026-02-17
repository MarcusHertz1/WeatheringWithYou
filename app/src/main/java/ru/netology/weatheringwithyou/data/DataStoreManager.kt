package ru.netology.weatheringwithyou.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.netology.weatheringwithyou.utils.City
import ru.netology.weatheringwithyou.utils.Language
import ru.netology.weatheringwithyou.utils.AppState
import ru.netology.weatheringwithyou.utils.Theme

val Context.datastore by preferencesDataStore(name = "preferences")

private val SELECTED_THEME = stringPreferencesKey("SELECTED_THEME")
private val SELECTED_CITY = stringPreferencesKey("SELECTED_CITY")
private val SELECTED_LANGUAGE = stringPreferencesKey("theme")

class DataStoreManager(private val context: Context) {

    private suspend fun setSelectedTheme(selectedTheme: String) {
        context.datastore.edit { preferences ->
            preferences[SELECTED_THEME] = selectedTheme
        }
    }

    private val getSelectedThemeFlow: Flow<String?> = context.datastore.data
        .map { prefs -> prefs[SELECTED_THEME] }

    private suspend fun setSelectedCity(selectedCity: String) {
        context.datastore.edit { preferences ->
            preferences[SELECTED_CITY] = selectedCity
        }
    }

    private val getSelectedCityFlow: Flow<String?> = context.datastore.data
        .map { prefs -> prefs[SELECTED_CITY] }

    private suspend fun setSelectedLanguage(selectedLanguage: String) {
        context.datastore.edit { preferences ->
            preferences[SELECTED_LANGUAGE] = selectedLanguage
        }
    }

    private val getSelectedLanguageFlow: Flow<String?> = context.datastore.data
        .map { prefs -> prefs[SELECTED_LANGUAGE] }

    val appState: Flow<AppState> = combine(
        getSelectedThemeFlow,
        getSelectedCityFlow,
        getSelectedLanguageFlow
    ) { themeName, cityName, languageName ->
        AppState(
            theme = themeName?.let { Theme.entries.firstOrNull { it.name == themeName } }
                ?: AppState().theme,
            city = cityName?.let { City.entries.firstOrNull { it.name == cityName } }
                ?: AppState().city,
            language = languageName?.let { Language.entries.firstOrNull { it.name == languageName } }
                ?: AppState().language
        )
    }

    suspend fun updateTheme(theme: Theme) {
        setSelectedTheme(theme.name)
    }

    suspend fun updateCity(city: City) {
        setSelectedCity(city.name)
    }

    suspend fun updateLanguage(language: Language) {
        setSelectedLanguage(language.name)
    }
}