package com.rapidex.rapidex_android_app.data.service

import com.rapidex.rapidex_android_app.data.api.TestApiService
import com.rapidex.rapidex_android_app.data.model.IncidentType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.io.IOException

class NetworkRapidexServiceTest {
    private var apiService = TestApiService()
    private val rapidexService = NetworkRapidexService(apiService)

    // NOMBRE = ESTADO -> CONDICIÓN -> RESULTADO

    @Before
    fun setUp(){
        apiService = TestApiService()
    }

    @Test
    fun getAllOrders() {
        runBlocking {
            val orders = rapidexService.getAllOrders()

            assertEquals(5, orders.size)
            assertEquals(0, orders[0].id)
            assertEquals(1, orders[1].id)
            assertEquals(2, orders[2].id)
            assertEquals(3, orders[3].id)
            assertEquals(4, orders[4].id)
        }
    }

    @Test
    fun getPendingOrders() {
        runBlocking {
            val orders = rapidexService.getPendingOrders()

            assertEquals(3, orders.size)
            assertEquals(2, orders[0].id)
            assertEquals(3, orders[1].id)
            assertEquals(4, orders[2].id)
        }
    }

    @Test
    fun getClaimedOrders() {
        runBlocking {
            val orders = rapidexService.getClaimedOrders(0)

            assertEquals(2, orders.size)
            assertEquals(0, orders[0].id)
            assertEquals(1, orders[1].id)
        }
    }

    @Test
    fun claimOrder() {
        runBlocking {
            rapidexService.claimOrder(2, 0)
            val orders = rapidexService.getClaimedOrders(0)

            assertEquals(3, orders.size)
            assertEquals(0, orders[0].id)
            assertEquals(1, orders[1].id)
            assertEquals(2, orders[2].id)
        }
    }

    @Test
    fun orderDone() {
        runBlocking {
            rapidexService.orderDone(0)
            val orders = rapidexService.getClaimedOrders(0)

            assertEquals(1, orders.size)
            assertEquals(1, orders[0].id)
        }
    }

    @Test
    fun loginValidCredentialsUserIsNotNull() {
        runBlocking {
            val employee = rapidexService.login("pmorralla", "1234")

            assertNotNull(employee)
            assertEquals(0, employee!!.id)
        }
    }

    @Test
    fun loginInvalidCredentialsUserIsNull() {
        runBlocking {
            val employee = rapidexService.login("pmorralla", "wrong")
            assertNull(employee)
        }
    }

    @Test
    fun sendIncidentExistingOrderDoesNotThrow() {
        runBlocking {
            rapidexService.sendIncident(IncidentType.OTHER, "", 0)
        }
    }

    @Test(expected = IOException::class)
    fun sendIncidentNonExistentOrderThrows(){
        runBlocking { rapidexService.sendIncident(IncidentType.OTHER, "", 999) }
    }
}