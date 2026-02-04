package com.rapidex.rapidex_android_app.data.api

import com.rapidex.rapidex_android_app.data.model.Incident
import com.rapidex.rapidex_android_app.data.model.Order
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("orders")
    suspend fun getOrders(): Response<List<Order>>

    @GET("orders/pending")
    suspend fun getPendingOrders(): Response<List<Order>>

    @POST("incidents")
    suspend fun createIncident(@Body incident: Incident): Response<Incident?>
}