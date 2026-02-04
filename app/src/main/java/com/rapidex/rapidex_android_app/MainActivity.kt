package com.rapidex.rapidex_android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.RapidexTheme
import com.rapidex.rapidex_android_app.ui.auth.AuthFlow
import com.rapidex.rapidex_android_app.ui.main.MainFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RapidexTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Auth"
                ){
                    composable("Auth"){
                        AuthFlow(
                            goToMainFlow = { navController.navigate("Main") }
                        )
                    }

                    composable("Main"){
                        MainFlow(
                            goToAuthFlow = { navController.navigate("Auth") }
                        )
                    }
                }
            }
        }
    }
}
