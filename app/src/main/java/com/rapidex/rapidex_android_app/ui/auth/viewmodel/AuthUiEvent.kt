package com.rapidex.rapidex_android_app.ui.auth.viewmodel

import androidx.annotation.StringRes

sealed class AuthUiEvent {
    data class ShowToast(@StringRes val stringRes: Int): AuthUiEvent()
    data class Navigate(val destination: AuthDestination): AuthUiEvent()
    object GoToMainFlow: AuthUiEvent()
}
