package com.rapidex.rapidex_android_app.domain

import com.rapidex.rapidex_android_app.data.model.Order

interface OrderRepository {
    /**
     * Gets all orders
     * @throws java.io.IOException on network error
     */
    suspend fun getAllOrders(): List<Order>

    /**
     * Gets all of the pending orders (they have employee, preparationDate, and dispatchDate all as null)
     * @throws java.io.IOException on network error
     */
    suspend fun getPendingOrders(): List<Order>

    /**
     * Gets all of the claimed orders (they have an employee and a preparationDate, but dispatchDate is null)
     * @throws java.io.IOException on network error
     */
    suspend fun getClaimedOrders(employeeId: Int): List<Order>

    /**
     * Claims an order for a specific employee
     * @throws java.io.IOException on network error
     */
    suspend fun claimOrder(orderId: Int, employeeId: Int)

    /**
     * Marks an order as done.
     * @throws java.io.IOException on network error
     */
    suspend fun orderDone(orderId: Int)
}