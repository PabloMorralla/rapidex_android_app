package com.rapidex.rapidex_android_app.ui.main

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
import com.rapidex.rapidex_android_app.ui.main.details.DetailsScreen
import com.rapidex.rapidex_android_app.ui.main.home.HomeScreen
import com.rapidex.rapidex_android_app.ui.main.incident.IncidentScreen
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainDestination
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainUiEvent
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainFlow(
    goToAuthFlow: ()->Unit
){
    val currentContext by rememberUpdatedState(LocalContext.current)
    val mainViewModel: MainViewModel = hiltViewModel()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(Unit) {
        mainViewModel.viewModelScope.launch {
            mainViewModel.refreshOrders()
        }

        mainViewModel.events.collect { event ->
            when (event) {
                is MainUiEvent.ShowToast -> {
                    Toast.makeText(currentContext, currentContext.getString(event.stringRes), Toast.LENGTH_LONG).show()
                }
                is MainUiEvent.Navigate -> {
                    navController.navigate(event.destination.route)
                }
                is MainUiEvent.GoToAuthFlow -> {
                    goToAuthFlow()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RapidexTopAppBar(
                title = when(backStackEntry?.destination?.route){
                    MainDestination.HOME.route -> stringResource(R.string.home_title)
                    MainDestination.DETAILS.route -> stringResource(R.string.details_title)
                    MainDestination.INCIDENT.route -> stringResource(R.string.incident_title)
                    else -> stringResource(R.string.error_title)
                },
                canNavigateBack = false,
                onBackNavigate = {}
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainDestination.HOME.route
        ) {
            composable(MainDestination.HOME.route) {
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    pendingOrders = mainViewModel.uiState.collectAsState().value.pendingOrders,
                    claimedOrders = mainViewModel.uiState.collectAsState().value.claimedOrders,
                    selectedOrderId = mainViewModel.uiState.collectAsState().value.selectedOrderId,
                    onSelectOrder = mainViewModel::selectOrder,
                    onClaimOrder = {
                        mainViewModel.viewModelScope.launch {
                            mainViewModel.claimOrder()
                        }
                    }
                )
            }

            composable(MainDestination.DETAILS.route) {
                DetailsScreen()
            }

            composable(MainDestination.INCIDENT.route) {
                IncidentScreen()
            }
        }
    }
}
