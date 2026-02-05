package com.rapidex.rapidex_android_app.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.IncidentType
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
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val employeeRepository: EmployeeRepository,
    private val orderRepository: OrderRepository,
    private val incidentRepository: IncidentRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(employee = employeeRepository.employee!!)) // TODO Please, do not cast nullable type to not-nullable type without checking its value first :)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<MainUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    suspend fun getPendingOrders(){
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
    suspend fun getClaimedOrders(){
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
    }

    suspend fun claimOrder(){
        if (_uiState.value.selectedOrderId == null){
            _eventChannel.send(MainUiEvent.ShowToast(R.string.unexpected_error))
            return
        }

        try {
            orderRepository.claimOrder(
                orderId = _uiState.value.selectedOrderId!!,
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

    suspend fun sendIncident(type: IncidentType, description: String, orderId: Int){
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
            currentState.copy(
                selectedOrderId = orderId
            )
        }
    }
}