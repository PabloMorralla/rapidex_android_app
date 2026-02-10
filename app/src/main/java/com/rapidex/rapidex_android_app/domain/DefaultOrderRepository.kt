package com.rapidex.rapidex_android_app.domain

import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.domain.OrderStatus
import com.rapidex.rapidex_android_app.data.service.RapidexService
import java.io.IOException

class DefaultOrderRepository(
    val service: RapidexService
): OrderRepository {
    override suspend fun getAllOrders(): List<Order> {
        try {
            return service.getAllOrders().map { it.copy(status = getOrderStatus(it)) }
        }
        catch (e: IOException) {
            throw e
        }
    }
    override suspend fun getPendingOrders(): List<Order>{
        try {
            return service.getPendingOrders().map { it.copy(status = getOrderStatus(it)) }
        }
        catch (e: IOException) {
            throw e
        }
    }
    override suspend fun getClaimedOrders(employeeId: Int): List<Order>{
        try {
            return service.getClaimedOrders(employeeId).map { it.copy(status = getOrderStatus(it)) }
        }
        catch (e: IOException) {
            throw e
        }
    }
    override suspend fun claimOrder(orderId: Int, employeeId: Int){
        try {
            service.claimOrder(orderId, employeeId)
        }
        catch (e: IOException) {
            throw e
        }
    }
    override suspend fun orderDone(orderId: Int){
        try {
            service.orderDone(orderId)
        }
        catch (e: IOException) {
            throw e
        }
    }

    fun getOrderStatus(order: Order): OrderStatus{
        return if (order.employee == null) OrderStatus.UNCLAIMED
            else OrderStatus.IN_PROCESS
    }
}
