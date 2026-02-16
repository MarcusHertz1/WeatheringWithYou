package ru.netology.weatheringwithyou.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.weatheringwithyou.R
import ru.netology.weatheringwithyou.ui.theme.WeatheringWithYouTheme

@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { SettingsTopBar(onBackClick = onBackClick) } // todo доделай
    ) { innerPadding ->
        SettingsScreenView(innerPadding)
    }
}

@Composable
private fun SettingsScreenView(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding),
        contentPadding = PaddingValues(
            vertical = 10.dp,
            horizontal = 16.dp
        )
    ) {
        settingsTitle(R.string.theme_choose)
        settingsSpacer()
        settingsRadioButton(textRes = R.string.light)
        settingsRadioButton(textRes = R.string.dark)
        settingsSpacer()

        settingsTitle(R.string.city_choose)
        settingsSpacer()
        settingsRadioButton(textRes = R.string.vsevolozsk)
        settingsRadioButton(textRes = R.string.moscow)
        settingsRadioButton(textRes = R.string.saint_petersburg)
        settingsRadioButton(textRes = R.string.grodno)
        settingsSpacer()

        settingsTitle(R.string.language_choose)
        settingsSpacer()
        settingsRadioButton(textRes = R.string.russian)
        settingsRadioButton(textRes = R.string.english)
        settingsSpacer()
    }
}

private fun LazyListScope.settingsRadioButton(
    textRes: Int,
    isSelected: Boolean = false,
    onSelect: () -> Unit = {},
) {
    item(contentType = RADIO_BUTTON_ITEM) {
        Row(
            modifier = Modifier
                .clickable { onSelect() }
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RadioButton(
                modifier = Modifier
                    .size(24.dp)
                    .padding(8.dp),
                selected = isSelected,
                onClick = { onSelect() }
            )
            SettingsText(textRes = textRes)
        }
    }
}

private fun LazyListScope.settingsSpacer() {
    item(contentType = SPACER_ITEM) {
        Spacer(modifier = Modifier.height(10.dp))
    }
}

private fun LazyListScope.settingsTitle(
    textRes: Int
) {
    item(contentType = TITLE_ITEM) {
        SettingsText(
            textRes = textRes,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
        )
    }
}

@Composable
private fun SettingsText(textRes: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(textRes),
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        color = MaterialTheme.colorScheme.primary
    )
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SettingsTopBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(R.string.settings))
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_back_24),
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
@Preview
private fun TitlePreview() {
    WeatheringWithYouTheme(dynamicColor = false) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            settingsTitle(R.string.theme_choose)
        }
    }
}

@Composable
@Preview
private fun RBPreview() {
    WeatheringWithYouTheme(dynamicColor = false) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            settingsRadioButton(textRes = R.string.light)
        }
    }
}

@Composable
@Preview
private fun SettingsScreenViewPreview() {
    WeatheringWithYouTheme(dynamicColor = false) {
        SettingsScreen {}
    }
}


const val TITLE_ITEM = "TITLE_ITEM"
const val RADIO_BUTTON_ITEM = "RADIO_BUTTON_ITEM"
const val SPACER_ITEM = "SPACER_ITEM"