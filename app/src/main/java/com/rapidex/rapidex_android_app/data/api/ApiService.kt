package com.rapidex.rapidex_android_app.data.api

import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.Incident
import com.rapidex.rapidex_android_app.data.model.LoginModel
import com.rapidex.rapidex_android_app.data.model.Order
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("orders")
    suspend fun getOrders(): Response<List<Order>>

    @GET("orders/pending")
    suspend fun getPendingOrders(): Response<List<Order>>

    @GET("claimed/{employeeId}")
    suspend fun getClaimedOrders(@Path("employeeId") employeeId: Int): Response<List<Order>>

    @POST("claim/{orderId}/{employeeId}")
    suspend fun claimOrder(@Path("orderId") orderId: Int, @Path("employeeId") employeeId: Int): Response<Unit>

    @DELETE("orders/{orderId}")
    suspend fun orderDone(@Path("orderId") orderId: Int): Response<Unit>

    @POST("employees/login")
    suspend fun login(@Body loginModel: LoginModel): Response<Employee>

    @POST("incidents")
    suspend fun createIncident(@Body incident: Incident): Response<Incident?>
}