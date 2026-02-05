package com.rapidex.rapidex_android_app.data.service

import com.rapidex.rapidex_android_app.data.api.ApiResponse
import com.rapidex.rapidex_android_app.data.api.ApiService
import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.Incident
import com.rapidex.rapidex_android_app.data.model.IncidentType
import com.rapidex.rapidex_android_app.data.model.LoginModel
import com.rapidex.rapidex_android_app.data.model.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class NetworkRapidexService(
    private val apiService: ApiService
): RapidexService {
    override suspend fun getAllOrders(): List<Order> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getOrders()

                if (response.isSuccessful){
                    response.body() ?: throw IOException("Empty body")
                }
                else {
                    throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
    override suspend fun getPendingOrders(): List<Order> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPendingOrders()

                if (response.isSuccessful){
                    response.body() ?: throw IOException("Empty body")
                }
                else {
                    throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
    override suspend fun getClaimedOrders(employeeId: Int): List<Order> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getClaimedOrders(employeeId)

                if (response.isSuccessful){
                    response.body() ?: throw IOException("Empty body")
                }
                else {
                    throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
    override suspend fun claimOrder(orderId: Int, employeeId: Int) {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.claimOrder(orderId, employeeId)

                if (!response.isSuccessful) {
                    throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
    override suspend fun orderDone(orderId: Int) {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.orderDone(orderId)

                if (!response.isSuccessful) {
                    throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
    override suspend fun login(username: String, password: String): Employee? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(LoginModel(username, password))

                if (response.isSuccessful){
                    response.body() ?: throw IOException("Empty body")
                }
                else {
                    if (response.code() == 400) null // Invalid credentials
                    else throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
    override suspend fun sendIncident(type: IncidentType, description: String, orderId: Int) {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.createIncident(
                    Incident(
                        typeString = type.stringRepresentation,
                        description = description,
                        orderId = orderId
                    )
                )

                if (!response.isSuccessful) {
                    throw IOException(response.message())
                }
            }
            catch (e: Exception) {
                throw IOException(e.message ?: "Unexpected error")
            }
        }
    }
}
