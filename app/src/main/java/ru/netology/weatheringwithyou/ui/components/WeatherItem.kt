package ru.netology.weatheringwithyou.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.weatheringwithyou.R
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme

@Composable
fun WeatherItem(icon: Painter, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(min = 44.dp)
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
@Preview
private fun WeatherItemPreview() {
    WeatheringWithYouTheme {
        WeatherItem(
            icon = painterResource(R.drawable.outline_device_thermostat_24),
            text = stringResource(R.string.temperature_description, "-8", "-11", "-11", "-8")
        )
    }
}