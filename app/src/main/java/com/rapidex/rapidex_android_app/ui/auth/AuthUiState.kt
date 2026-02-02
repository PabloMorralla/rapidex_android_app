package com.rapidex.rapidex_android_app.ui.auth

import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.Order

data class AuthUiState (
    val employee: Employee? = null,
    val pendingOrders: List<Order> = emptyList(),
    val claimedOrders: List<Order> = emptyList()
)
