package com.rapidex.rapidex_android_app.data.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.model.IncidentType
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.data.model.Product
import java.io.IOException
import java.time.LocalDateTime

class TestRapidexService: RapidexService {
    private val employees = mutableListOf(
        Employee(0, "Pablo", "Morralla", "pmorralla", "pablo1234"),
        Employee(1, "Darius", "Tiganas", "dtiganas", "darius1234"),
        Employee(2, "Guillermo", "Campos", "gcampos", "guillermo1234"),
        Employee(3, "David", "Sánchez", "dsanchez", "david1234"),
        Employee(4, "Ion", "León", "ileon", "ion1234"),
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
    private val orders = mutableListOf(
        Order(id = 0, products = listOf(products[0], products[5], products[2])),
        Order(id = 1, products = listOf(products[3], products[7], products[4])),
        Order(id = 2, products = listOf(products[9], products[6], products[3])),
        Order(id = 3, products = listOf(products[8], products[1], products[5])),
        Order(id = 4, products = listOf(products[7], products[2], products[5])),
    )

    override fun getPendingOrders(): List<Order> {
        return orders.filter { it.preparationDate == null }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun claimOrder(orderId: Int, employeeId: Int) {
        var order = orders.find { it.id == orderId }
        if (order == null) throw IOException("Order not found")

        val employee = employees.find {it.id == employeeId}
        if (employee == null) throw IOException("Employee not found")

        orders.remove(order)
        order = order.copy(employee = employee, preparationDate = LocalDateTime.now())
        orders.add(order)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun orderDone(orderId: Int) {
        var order = orders.find{it.id == orderId}
        if (order == null) throw IOException("Order not found")

        orders.remove(order)
        order = order.copy(dispatchDate = LocalDateTime.now())
        orders.add(order)
    }

    override fun sendIncident(type: IncidentType, description: String) {}

    override fun login(username: String, password: String): Employee? {
        return employees.find { it.username == username && it.password == password}
    }
}
