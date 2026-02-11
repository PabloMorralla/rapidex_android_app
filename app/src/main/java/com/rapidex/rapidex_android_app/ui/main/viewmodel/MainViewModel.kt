package com.rapidex.rapidex_android_app.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.IncidentType
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.domain.OrderStatus
import com.rapidex.rapidex_android_app.domain.EmployeeRepository
import com.rapidex.rapidex_android_app.domain.IncidentRepository
import com.rapidex.rapidex_android_app.domain.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val employeeRepository: EmployeeRepository,
    private val orderRepository: OrderRepository,
    private val incidentRepository: IncidentRepository
): ViewModel() {
    init {
        viewModelScope.launch {
            refreshOrders()
        }
    }

    private val _uiState = MutableStateFlow(MainUiState(employee = employeeRepository.employee!!)) // TODO Please, do not cast nullable type to not-nullable type without checking its value first :)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<MainUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    private suspend fun getPendingOrders(){
        try {
            val pendingOrders = orderRepository.getPendingOrders()
            _uiState.update { currentState ->
                currentState.copy(
                    pendingOrders = pendingOrders
                )
            }
        }
        catch (e: IOException) {
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_loading_pending_orders))
        }
    }
    private suspend fun getClaimedOrders(){
        try {
            val claimedOrders = orderRepository.getClaimedOrders(_uiState.value.employee.id)
            _uiState.update { currentState ->
                currentState.copy(
                    claimedOrders = claimedOrders
                )
            }
        }
        catch (e: IOException) {
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_loading_claimed_orders))
        }
    }
    suspend fun refreshOrders(){
        getPendingOrders()
        getClaimedOrders()

        _uiState.update { currentState ->
            currentState.copy(
                selectedOrder = null
            )
        }
    }

    suspend fun claimOrder(){
        if (_uiState.value.selectedOrder == null){
            _eventChannel.send(MainUiEvent.ShowToast(R.string.unexpected_error))
            return
        }

        try {
            orderRepository.claimOrder(
                orderId = _uiState.value.selectedOrder!!.id,
                employeeId = _uiState.value.employee.id
            )

            refreshOrders()
            _eventChannel.send(MainUiEvent.ShowToast(R.string.success_claim_order))
        }
        catch (e: Exception) {
            _eventChannel.send(MainUiEvent.ShowToast(R.string.unexpected_error))
        }
    }
    suspend fun orderDone(orderId: Int) {
        val order = _uiState.value.claimedOrders.find { it.id == orderId }

        if (order == null) {
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_order_done))
            return
        }

        if (order.status != OrderStatus.FINISHED){
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_order_unfinished))
            return
        }

        try {
            orderRepository.orderDone(orderId)

            refreshOrders()
            _eventChannel.send(MainUiEvent.Navigate(MainDestination.HOME))
            _eventChannel.send(MainUiEvent.ShowToast(R.string.success_order_done))
        }
        catch (e: IOException){
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_order_done))
        }
    }

    suspend fun sendIncident(type: IncidentType?, description: String, orderId: Int){
        if (type == null){
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_no_type_selected))
            return
        }

        try {
            incidentRepository.sendIncident(type, description, orderId)

            refreshOrders()
            _eventChannel.send(MainUiEvent.Navigate(MainDestination.HOME))
            _eventChannel.send(MainUiEvent.ShowToast(R.string.success_send_incident))
        } catch (e: IOException) {
            _eventChannel.send(MainUiEvent.ShowToast(R.string.error_send_incident))
        }
    }

    fun selectOrder(orderId: Int){
        _uiState.update { currentState ->
            val order = currentState.claimedOrders.find { it.id == orderId }
                ?: currentState.pendingOrders.find { it.id == orderId }

            currentState.copy(
                selectedOrder = order
            )
        }
    }
    fun toggleProductAsDone(orderId: Int, productIndex: Int) {
        _uiState.update { state ->
            val updatedOrders = state.claimedOrders.map { order ->
                if (order.id != orderId) return@map order

                val updatedProducts = order.products.mapIndexed { index, product ->
                    if (index == productIndex)
                        product.copy(done = !product.done)
                    else
                        product
                }

                val updatedStatus = if (updatedProducts.all{it.done}) OrderStatus.FINISHED
                    else OrderStatus.IN_PROCESS

                order.copy(
                    products = updatedProducts,
                    status = updatedStatus
                )
            }

            state.copy(
                claimedOrders = updatedOrders,
                selectedOrder = updatedOrders.find { it.id == orderId }
            )
        }
    }
}