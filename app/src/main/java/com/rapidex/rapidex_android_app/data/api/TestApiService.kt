package com.rapidex.rapidex_android_app.data.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.Incident
import com.rapidex.rapidex_android_app.data.model.LoginModel
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.data.model.Product
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime

class TestApiService: ApiService {
    private val employees = mutableListOf(
        Employee(0, "Pablo", "Morralla", "pmorralla", "1234"),
        Employee(1, "Darius", "Tiganas", "dtiganas", "1234"),
        Employee(2, "Guillermo", "Campos", "gcampos", "1234"),
        Employee(3, "David", "Sánchez", "dsanchez", "1234"),
        Employee(4, "Ion", "León", "ileon", "1234"),
    )
    private val products = listOf(
        Product(
            0,
            "Wireless Headphones",
            "Electronics",
            "Over-ear Bluetooth headphones with noise cancellation and 30-hour battery life."
        ),
        Product(
            1,
            "Smart Watch",
            "Electronics",
            "Water-resistant smartwatch with heart-rate tracking, GPS, and sleep monitoring."
        ),
        Product(
            2,
            "Espresso Maker",
            "Home & Kitchen",
            "Compact espresso machine with 15-bar pressure and milk frother."
        ),
        Product(
            3,
            "Yoga Mat",
            "Fitness",
            "Non-slip yoga mat made from eco-friendly materials for all workout levels."
        ),
        Product(
            4,
            "Desk Lamp",
            "Office",
            "LED desk lamp with adjustable brightness, color temperature, and USB charging port."
        ),
        Product(
            5,
            "Running Shoes",
            "Footwear",
            "Lightweight running shoes with breathable mesh and cushioned soles."
        ),
        Product(
            6,
            "Backpack",
            "Accessories",
            "Durable backpack with padded laptop compartment and multiple storage pockets."
        ),
        Product(
            7,
            "Cookbook",
            "Books",
            "Recipe book featuring quick, healthy meals with step-by-step instructions."
        ),
        Product(
            8,
            "Bluetooth Speaker",
            "Electronics",
            "Portable Bluetooth speaker with deep bass and 12-hour playtime."
        ),
        Product(
            9,
            "Water Bottle",
            "Fitness",
            "Insulated stainless steel water bottle that keeps drinks cold for 24 hours."
        )
    )
    @RequiresApi(Build.VERSION_CODES.O)
    private val orders = mutableListOf(
        Order(id = 0, employee = employees[0], preparationDate = LocalDateTime.now().toString(), products = listOf(products[0], products[5], products[2])),
        Order(id = 1, employee = employees[0], preparationDate = LocalDateTime.now().toString(), products = listOf(products[3], products[7], products[4])),
        Order(id = 2, products = listOf(products[9], products[6], products[3])),
        Order(id = 3, products = listOf(products[8], products[1], products[5])),
        Order(id = 4, products = listOf(products[7], products[2], products[5])),
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getOrders(): Response<List<Order>> {
        return Response.success(
            orders.toList()
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPendingOrders(): Response<List<Order>> {
        return Response.success(
            orders.filter { it.preparationDate == null }
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getClaimedOrders(employeeId: Int): Response<List<Order>> {
        return Response.success(
            orders.filter { order ->
                order.employee?.id == employeeId
                && order.preparationDate != null
                && order.dispatchDate == null
            }
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun claimOrder(orderId: Int, employeeId: Int): Response<Unit> {
        var order = orders.find { it.id == orderId }
        if (order == null) return Response.error(400, "".toResponseBody(null))

        val employee = employees.find {it.id == employeeId}
        if (employee == null) return Response.error(400, "".toResponseBody(null))

        orders.remove(order)
        order = order.copy(employee = employee, preparationDate = LocalDateTime.now().toString())
        orders.add(order)

        return Response.success(Unit)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun orderDone(orderId: Int): Response<Unit> {
        var order = orders.find{it.id == orderId}
        if (order == null) return Response.error(400, "".toResponseBody(null))
        if (order.employee == null) return Response.error(400, "".toResponseBody(null))

        orders.remove(order)
        order = order.copy(dispatchDate = LocalDateTime.now().toString())
        orders.add(order)

        return Response.success(Unit)
    }
    override suspend fun login(loginModel: LoginModel): Response<Employee> {
        val employee = employees.find { it.username == loginModel.username && it.password == loginModel.password}

        return (
            if (employee == null)
                Response.error(400, "".toResponseBody(null))
            else
                Response.success(employee)
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createIncident(incident: Incident): Response<Incident?> {
        return if (orders.find {it.id == incident.orderId} == null)
            Response.error(404, "".toResponseBody(null))
        else
            Response.success(incident)
    }
}
