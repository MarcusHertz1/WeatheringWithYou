package ru.netology.weatheringwithyou.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.netology.weatheringwithyou.R
import ru.netology.weatheringwithyou.domain.WeatherData
import ru.netology.weatheringwithyou.ui.components.WeatherItem
import ru.netology.weatheringwithyou.ui.components.WeatherTopbar
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    goToSettings: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val error = remember(state.error) { state.error }
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { viewModel.applyAction(MainActions.LoadWeather) }
    ) {
        when {
            state.isLoading -> LoadingView()
            error != null -> ErrorView(error)
            state.weatherData == null -> ErrorView(
                stringResource(R.string.error_no_weather_data),
                onRetryClick = { viewModel.applyAction(MainActions.LoadWeather) },
                showRetryButton = true,
            )

            else -> {
                state.weatherData?.let { weatherData ->
                    WeatherView(
                        city = stringResource(state.selectedCity.cityRes),
                        weatherData = weatherData,
                        goToSettings = goToSettings
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherView(
    city: String,
    weatherData: WeatherData,
    goToSettings: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        val imageModifier = weatherData.weather.imageRes?.let{
            Modifier.paint(
                painterResource(id = weatherData.weather.backgroundRes),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            )
        } ?: Modifier
        Image(
            painter = painterResource(
                weatherData.weather.imageRes
                    ?: weatherData.weather.backgroundRes
            ),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = imageModifier
                .height(280.dp)
                .fillMaxWidth()
        )
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                WeatherTopbar(
                    city = city,
                    temperature = weatherData.temperature.toString(),
                    weatherText = stringResource(weatherData.weather.weatherNameRes),
                    onSettingsClick = goToSettings,
                    scrollBehavior = scrollBehavior,
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 412.dp)
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                item {
                    WeatherItem(
                        text = stringResource(
                            id = R.string.temperature_description,
                            weatherData.temperature.toString(),
                            weatherData.feelsLike.toString(),
                            weatherData.temperatureMin.toString(),
                            weatherData.temperatureMax.toString()
                        ),
                        icon = painterResource(R.drawable.outline_device_thermostat_24),
                    )
                }
                item {
                    WeatherItem(
                        text = stringResource(
                            id = R.string.pressure_and_humidity_description,
                            weatherData.pressure.toString(),
                            weatherData.humidity.toString()
                        ),
                        icon = painterResource(R.drawable.outline_humidity_mid_24),
                    )
                }
                item {
                    WeatherItem(
                        text = stringResource(
                            id = R.string.wind_speed_and_visibility_description,
                            weatherData.visibility.toString(),
                            weatherData.windSpeed.toString()
                        ),
                        icon = painterResource(R.drawable.outline_air_24),
                    )
                }
                item { Separator() }
                item {
                    SunsetAndSunriseInfo(
                        sunset = weatherData.sunset,
                        sunrise = weatherData.sunrise
                    )
                }
            }
        }
    }
}

@Composable
private fun SunsetAndSunriseInfo(
    sunset: String,
    sunrise: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(13.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(46.dp)) {
            BrightnessIcon(R.drawable.outline_brightness_5_24)
            BrightnessIcon(R.drawable.outline_brightness_6_24)
            BrightnessIcon(R.drawable.outline_brightness_7_24)
        }
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = stringResource(
                R.string.sunrise_and_sunset_description,
                sunrise,
                sunset
            )
        )
    }
}

@Composable
private fun BrightnessIcon(drawable: Int) {
    Icon(
        painter = painterResource(drawable),
        contentDescription = null,
        modifier = Modifier.size(24.dp),
        tint = MaterialTheme.colorScheme.primary,
    )
}

@Composable
private fun Separator() {
    Box(
        modifier = Modifier
            .height(1.dp)
            .width(241.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp)
    )
}

@Composable
private fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {}
}

@Composable
private fun ErrorView(
    errorText: String,
    onRetryClick: () -> Unit = {},
    showRetryButton: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorText)
        if (showRetryButton) Button(onClick = onRetryClick) { Text(text = stringResource(R.string.reload)) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    WeatheringWithYouTheme(dynamicColor = false) {
        MainScreen({})
    }
}