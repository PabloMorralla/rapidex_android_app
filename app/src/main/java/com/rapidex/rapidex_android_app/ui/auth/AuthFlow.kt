package com.rapidex.rapidex_android_app.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.ui.auth.login.LoginScreen
import com.rapidex.rapidex_android_app.ui.auth.viewmodel.AuthDestination
import com.rapidex.rapidex_android_app.ui.auth.viewmodel.AuthUiEvent
import com.rapidex.rapidex_android_app.ui.auth.viewmodel.AuthViewModel
import com.rapidex.rapidex_android_app.ui.components.RapidexTopAppBar
import kotlinx.coroutines.launch

@Composable
fun AuthFlow(
    goToMainFlow: ()->Unit
){
    val currentContext by rememberUpdatedState(LocalContext.current)
    val authViewModel: AuthViewModel = hiltViewModel()

    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        authViewModel.events.collect { event ->
            when (event) {
                is AuthUiEvent.ShowToast -> {
                    Toast.makeText(currentContext, currentContext.getString(event.stringRes), Toast.LENGTH_LONG).show()
                }
                is AuthUiEvent.Navigate -> {
                    navController.navigate(event.destination.route)
                }
                is AuthUiEvent.GoToMainFlow -> {
                    goToMainFlow()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RapidexTopAppBar(
                title = stringResource(R.string.login_title),
                canNavigateBack = false,
                onBackNavigate = {}
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AuthDestination.LOGIN.route
        ) {
            composable(AuthDestination.LOGIN.route) {
                LoginScreen(
                    modifier = Modifier.padding(innerPadding),
                    onLogin = { username, password ->
                        authViewModel.viewModelScope.launch {
                            authViewModel.login(username, password)
                        }
                    }
                )
            }
        }
    }
}