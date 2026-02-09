package com.rapidex.rapidex_android_app.ui.main

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.ui.components.RapidexTopAppBar
import com.rapidex.rapidex_android_app.ui.main.details.DetailsScreen
import com.rapidex.rapidex_android_app.ui.main.home.HomeScreen
import com.rapidex.rapidex_android_app.ui.main.incident.IncidentScreen
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainDestination
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainUiEvent
import com.rapidex.rapidex_android_app.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.rapidex.rapidex_android_app.ui.components.MainBottomNavBar

@Composable
fun MainFlow(
    goToAuthFlow: ()->Unit
){
    val currentContext by rememberUpdatedState(LocalContext.current)
    val mainViewModel: MainViewModel = hiltViewModel()

    val destinations = MainDestination.entries.toList()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { MainDestination.entries.size }
    )

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        mainViewModel.events.collect { event ->
            when (event) {
                is MainUiEvent.ShowToast -> {
                    Toast.makeText(currentContext, currentContext.getString(event.stringRes), Toast.LENGTH_LONG).show()
                }
                is MainUiEvent.Navigate -> {
                    pagerState.animateScrollToPage(event.destination.ordinal)
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
                title = when(destinations[pagerState.currentPage].label){
                    MainDestination.HOME.label -> stringResource(R.string.home_title)
                    MainDestination.DETAILS.label -> stringResource(R.string.details_title)
                    MainDestination.INCIDENT.label -> stringResource(R.string.incident_title)
                    else -> stringResource(R.string.error_title)
                },
                canNavigateBack = false,
                onBackNavigate = {}
            )
        },
        bottomBar = {
            MainBottomNavBar(
                entries = destinations,
                currentPageIndex = pagerState.currentPage,
                onNavigate = { i ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(i)
                    }
                }
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(innerPadding)
        ) { page ->
            when(page){
                MainDestination.HOME.ordinal -> HomeScreen(
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

                MainDestination.DETAILS.ordinal -> DetailsScreen(
                    order = mainViewModel.getSelectedOrder(),
                    getOrderStatus = mainViewModel::getOrderStatus
                )

                MainDestination.INCIDENT.ordinal -> IncidentScreen()
            }
        }
    }
}
