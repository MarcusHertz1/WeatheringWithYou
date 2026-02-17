package ru.netology.weatheringwithyou

import android.app.Application
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import ru.netology.weatheringwithyou.data.DataStoreManager
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme
import ru.netology.weatheringwithyou.utils.AppState
import ru.netology.weatheringwithyou.utils.Theme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val dataStoreManager = remember { DataStoreManager(context) }
            val appState by dataStoreManager.appState.collectAsState(initial = AppState())
            val currentLanguage = remember { mutableStateOf(appState.language.languageCode) }
            val languageCode = appState.language.languageCode
            LaunchedEffect(languageCode) {
                if (currentLanguage.value!=languageCode) {
                    currentLanguage.value = languageCode
                    setLanguage(languageCode)
                }
            }
            WeatheringWithYouTheme(
                dynamicColor = false,
                darkTheme = appState.theme == Theme.DARK,
            ) {
                AppNavigation()
            }
        }
    }

    fun setLanguage(languageTag: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(languageTag)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}

@HiltAndroidApp
class App : Application()