package com.rapidex.rapidex_android_app.ui.main.viewmodel

import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.Order

data class MainUiState (
    val employee: Employee,
    val pendingOrders: List<Order> = emptyList(),
    val claimedOrders: List<Order> = emptyList(),
    val selectedOrderId: Int? = null
)
