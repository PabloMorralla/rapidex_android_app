package com.rapidex.rapidex_android_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.RapidexTheme
import com.rapidex.rapidex_android_app.ui.login.LoginScreen
import com.rapidex.rapidex_android_app.ui.main.MainScreen
import com.rapidex.rapidex_android_app.ui.auth.AuthDestination
import com.rapidex.rapidex_android_app.ui.auth.AuthViewModel
import com.rapidex.rapidex_android_app.ui.auth.AuthUiEvent
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
                    val authViewModel: AuthViewModel = hiltViewModel()

                    val navController = rememberNavController()

                    LaunchedEffect(Unit) {
                        authViewModel.events.collect { event ->
                            when (event) {
                                is AuthUiEvent.ShowToast -> {
                                    Toast.makeText(context, context.getString(event.stringRes), Toast.LENGTH_LONG).show()
                                }
                                is AuthUiEvent.Navigate -> {
                                    navController.navigate(event.destination.route)
                                }
                            }
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = AuthDestination.LOGIN.route
                    ) {
                        composable(AuthDestination.LOGIN.route){
                            LoginScreen()
                        }

                        composable(AuthDestination.MAIN.route){
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}
