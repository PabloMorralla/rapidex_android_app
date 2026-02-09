package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainDestination

@Composable
fun MainBottomNavBar (
    entries: List<MainDestination>,
    currentPageIndex: Int,
    onNavigate: (Int)->Unit
) {
    NavigationBar {
        entries.forEachIndexed { i, entry ->
            NavigationBarItem(
                selected = (currentPageIndex == i),
                onClick = { onNavigate(i) },
                icon = {
                    Icon(
                        imageVector = entry.icon,
                        contentDescription = entry.label
                    )
                },
                label = { Text(entry.label) }
            )
        }
    }
}