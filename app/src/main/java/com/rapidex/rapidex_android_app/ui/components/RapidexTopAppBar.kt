package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RapidexTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    onBackNavigate: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onBackNavigate
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            Image(
                painter = painterResource(R.drawable.logo_rapidex_trinidad),
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
    )
}