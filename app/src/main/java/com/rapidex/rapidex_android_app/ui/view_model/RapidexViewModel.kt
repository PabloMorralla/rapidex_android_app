package com.rapidex.rapidex_android_app.ui.view_model

import androidx.lifecycle.ViewModel
import com.rapidex.rapidex_android_app.data.service.RapidexService
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
class RapidexViewModel @Inject constructor (
    private val rapidexService: RapidexService
): ViewModel() {
    private val _uiState = MutableStateFlow(RapidexUiState())
    val uiState: StateFlow<RapidexUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<UiEvent>()
    val events = _eventChannel.receiveAsFlow()

    suspend fun login(username: String, password: String){
        try {
            val employee = rapidexService.login(username, password)
            if (employee == null) {
                _eventChannel.send(UiEvent.ShowToast("Invalid Credentials"))
            }
            else {
                _uiState.update { currentState ->
                    currentState.copy(
                        employee = employee
                    )
                }
            }
        }
        catch (e: IOException) {
            _eventChannel.send(UiEvent.ShowToast("Unexpected Error"))
        }
    }
}
