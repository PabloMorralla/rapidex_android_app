package com.rapidex.rapidex_android_app.data.service

import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.IncidentType
import com.rapidex.rapidex_android_app.data.model.Order

interface RapidexService {
    /**
     * Gets all of the pending orders (they have employee, preparationDate, and dispatchDate all as null)
     */
    fun getPendingOrders(): List<Order>

    /**
     * Claims an order for a specific employee
     */
    fun claimOrder(orderId: Int, employeeId: Int)

    /**
     * Marks an order as done.
     */
    fun orderDone(orderId: Int)

    /**
     * Sends an incident to the datacenter
     */
    fun sendIncident(type: IncidentType, description: String)

    /**
     * This function does a login attempt.
     * @return An `Employee` object on successful login and `null` on incorrect credentials
     * @throws java.io.IOException on network error
     */
    fun login(username: String, password: String): Employee?
}

