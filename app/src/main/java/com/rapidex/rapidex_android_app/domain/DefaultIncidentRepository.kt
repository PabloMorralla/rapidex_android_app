package com.rapidex.rapidex_android_app.domain

import com.rapidex.rapidex_android_app.data.model.IncidentType
import com.rapidex.rapidex_android_app.data.service.RapidexService
import java.io.IOException

class DefaultIncidentRepository(
    val service: RapidexService
): IncidentRepository {
    override suspend fun sendIncident(type: IncidentType, description: String, orderId: Int) {
        try {
            service.sendIncident(type, description, orderId)
        }
        catch (e: IOException) {
            throw e
        }
    }
}