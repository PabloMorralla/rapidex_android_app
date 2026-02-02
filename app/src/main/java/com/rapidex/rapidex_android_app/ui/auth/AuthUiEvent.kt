package com.rapidex.rapidex_android_app.ui.auth

import androidx.annotation.StringRes

sealed class AuthUiEvent {
    data class ShowToast(@StringRes val stringRes: Int): AuthUiEvent()
    data class Navigate(val destination: AuthDestination): AuthUiEvent()
}
