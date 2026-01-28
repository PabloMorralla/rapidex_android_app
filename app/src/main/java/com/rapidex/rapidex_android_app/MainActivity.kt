package com.rapidex.rapidex_android_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.RapidexTheme
import com.rapidex.rapidex_android_app.ui.view_model.RapidexViewModel
import com.rapidex.rapidex_android_app.ui.view_model.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RapidexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val viewModel: RapidexViewModel = hiltViewModel()
                    val uiState by viewModel.uiState.collectAsState()

                    LaunchedEffect(Unit) {
                        viewModel.events.collect { event ->
                            when (event) {
                                is UiEvent.ShowToast -> {
                                    Toast.makeText(context, event.message, Toast.LENGTH_LONG)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}