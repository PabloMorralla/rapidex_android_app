package com.rapidex.rapidex_android_app.ui.main.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainDestination (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    HOME("Home", "Home", Icons.Default.Home),
    DETAILS("Details", "Details", Icons.Default.Build),
    INCIDENT("Incident", "Incident", Icons.Default.Warning)
}