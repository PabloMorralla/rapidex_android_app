package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MutedButton (
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
    )
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = colors
    ) {
        Text(
            text = text
        )
    }
}
