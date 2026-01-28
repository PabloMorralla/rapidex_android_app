package com.rapidex.rapidex_android_app.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rapidex.rapidex_android_app.data.service.TestRapidexService

class TestRapidexViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RapidexViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RapidexViewModel(TestRapidexService()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
