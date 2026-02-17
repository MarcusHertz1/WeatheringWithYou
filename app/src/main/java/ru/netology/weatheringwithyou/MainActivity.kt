package ru.netology.weatheringwithyou

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import ru.netology.weatheringwithyou.data.DataStoreManager
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme
import ru.netology.weatheringwithyou.utils.AppState
import ru.netology.weatheringwithyou.utils.Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val dataStoreManager = remember { DataStoreManager(context) }
            val appState by dataStoreManager.appState.collectAsState(initial = AppState())
            WeatheringWithYouTheme(
                dynamicColor = false,
                darkTheme = appState.theme == Theme.DARK,
            ) {
                AppNavigation()
            }
        }
    }
}

@HiltAndroidApp
class App : Application()