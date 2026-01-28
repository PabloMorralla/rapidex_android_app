package com.rapidex.rapidex_android_app.ui.view_model

sealed class UiEvent {
    data class ShowToast(val message: String): UiEvent()
}
