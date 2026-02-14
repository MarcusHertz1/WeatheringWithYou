package ru.netology.weatheringwithyou.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import ru.netology.weatheringwithyou.R
import ru.netology.weatheringwithyou.ui.components.WeatherItem
import ru.netology.weatheringwithyou.ui.components.WeatherTopbar
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Image(
            painter = painterResource(R.drawable.broken_clouds),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth()
                .paint(
                    painterResource(id = R.drawable.broken_clouds_gradient),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopCenter
                )
        )
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                WeatherTopbar(
                    city = stringResource(R.string.vsevolozsk), // todo доработать
                    temperature = "-11", // todo доработать
                    weatherText = "Облачно", //todo доработай
                    onSettingsClick = {}, //todo доработай
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
                items((1..3).toList()) { item -> //todo доработай
                    WeatherItem(
                        text = "Item $item",
                        icon = painterResource(R.drawable.outline_device_thermostat_24),
                    )
                }
                item { Separator() }
                item { SunsetAndSunriseInfo() }
            }
        }
    }
}

@Composable
private fun SunsetAndSunriseInfo() {
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
                "8:00",
                "18:00"
            ) // todo доработай
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    WeatheringWithYouTheme(dynamicColor = false) {
        MainScreen()
    }
}