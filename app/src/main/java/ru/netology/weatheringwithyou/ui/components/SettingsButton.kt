package ru.netology.weatheringwithyou.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.netology.weatheringwithyou.R

@Composable
fun SettingsButton(
    onSettingsClick: () -> Unit,
    buttonTint: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
    onClick = onSettingsClick
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.Settings,
            tint = buttonTint,
            contentDescription = stringResource(R.string.go_to_settings)
        )
    }
}