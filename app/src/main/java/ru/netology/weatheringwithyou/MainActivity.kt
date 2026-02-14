package ru.netology.weatheringwithyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.netology.weatheringwithyou.ui.screens.SettingsScreen
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatheringWithYouTheme (dynamicColor = false) {
                SettingsScreen({})
            }
        }
    }
}