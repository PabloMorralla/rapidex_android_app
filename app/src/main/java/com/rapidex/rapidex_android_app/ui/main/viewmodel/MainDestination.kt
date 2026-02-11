package com.rapidex.rapidex_android_app.ui.main.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainDestination (
    val label: String,
    val icon: ImageVector
) {
    HOME("Home", Icons.Default.Home),
    DETAILS("Details", Icons.Default.Build),
    INCIDENT("Incident", Icons.Default.Warning)
}