package com.rapidex.rapidex_android_app.domain

import com.rapidex.rapidex_android_app.data.model.IncidentType

interface IncidentRepository {
    /**
     * Sends an incident to the datacenter
     */
    suspend fun sendIncident(type: IncidentType, description: String, orderId: Int)
}