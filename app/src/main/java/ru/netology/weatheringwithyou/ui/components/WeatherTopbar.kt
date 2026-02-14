package ru.netology.weatheringwithyou.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.netology.weatheringwithyou.R
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopbar(
    city: String,
    temperature: String,
    weatherText: String,
    onSettingsClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    MediumTopAppBar(
        modifier = Modifier.padding(end = 16.dp),
        collapsedHeight = TOPBAR_COLLAPSED_HEIGHT.dp,
        expandedHeight = TOPBAR_EXPANDED_HEIGHT.dp,
        title = {
            TopbarTitle(
                city = city,
                temperature = temperature,
                weatherText = weatherText,
                scrollBehavior = scrollBehavior,
            )
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = Color.Transparent
        ),
        actions = {
            IconButton(
                onClick = onSettingsClick
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Settings,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(R.string.go_to_settings)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopbarTitle(
    city: String,
    temperature: String,
    weatherText: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val collapsingIndex = scrollBehavior?.state?.collapsedFraction ?: 0f
    val isCollapsed = collapsingIndex > 0.5f
    val alpha = when {
        collapsingIndex <= 0.3f -> 1f - collapsingIndex / 0.3f
        collapsingIndex >= 0.8f -> (collapsingIndex - 0.8f) / 0.3f
        else -> 0f
    }.coerceIn(0f, 1f)
    Column(
        modifier = Modifier
            .alpha(alpha)
            .widthIn(max = 412.dp),
        verticalArrangement =
            if (isCollapsed) Arrangement.Center
            else Arrangement.Top
    ) {
        if (isCollapsed) Text(text = city)
        else {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = city)
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        WeatherLabel(weatherText = weatherText)
                    }
                    Text(
                        text = "${temperature}°",
                        fontSize = 96.sp,
                    )
                }
            }
        }
    }
}

@Composable
private fun WeatherLabel(
    weatherText: String,
    modifier: Modifier = Modifier,
) {
    val surfaceColor = remember { Color(0x33000000) }

    val gradientBrushStart = remember(surfaceColor) {
        Brush.horizontalGradient(
            colors = listOf(surfaceColor.copy(alpha = 0f), surfaceColor)
        )
    }
    val gradientBrushEnd = remember(surfaceColor) {
        Brush.horizontalGradient(
            colors = listOf(surfaceColor, surfaceColor.copy(alpha = 0f))
        )
    }
    Row(
        modifier = modifier
            .padding(bottom = 32.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.Bottom,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(brush = gradientBrushStart)
                .width(24.dp)
        )
        Text(
            modifier = Modifier
                .background(surfaceColor),
            text = weatherText,
            fontSize = 20.sp,
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(24.dp)
                .background(brush = gradientBrushEnd)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WeatherTopbarPreview() {
    WeatheringWithYouTheme(dynamicColor = false) {
        WeatherTopbar(
            city = "Всеволожск",
            temperature = "-12",
            weatherText = "Облачно",
            onSettingsClick = {}
        )
    }
}
private const val TOPBAR_EXPANDED_HEIGHT = 280
private const val TOPBAR_COLLAPSED_HEIGHT = 44