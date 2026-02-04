package com.rapidex.rapidex_android_app.ui.main.viewmodel

import androidx.annotation.StringRes

sealed class MainUiEvent {
    data class ShowToast(@StringRes val stringRes: Int): MainUiEvent()
    data class Navigate(val destination: MainDestination): MainUiEvent()
    object GoToAuthFlow: MainUiEvent()
}