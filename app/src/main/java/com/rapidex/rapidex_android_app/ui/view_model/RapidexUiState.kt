package com.rapidex.rapidex_android_app.ui.view_model

import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.Order

data class RapidexUiState (
    val employee: Employee? = null,
    val pendingOrders: List<Order> = emptyList(),
    val activeOrders: List<Order> = emptyList()
)
