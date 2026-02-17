package ru.netology.weatheringwithyou

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ru.netology.weatheringwithyou.ui.screens.MainScreen
import ru.netology.weatheringwithyou.ui.screens.settings.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main.toString()) {
        composable(route = Settings.toString()) {
            SettingsScreen() { navController.popBackStack() }
        }
        composable(route = Main.toString()) {
            MainScreen { navController.navigate(Settings.toString()) }
        }
    }
}

@Serializable
object Main

@Serializable
object Settings