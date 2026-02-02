package com.rapidex.rapidex_android_app.ui.auth

import androidx.lifecycle.ViewModel
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.domain.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val employeeRepository: EmployeeRepository
): ViewModel() {
    private val _eventChannel = Channel<AuthUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    suspend fun login(username: String, password: String){
        try {
            val employee = employeeRepository.login(username, password)
            if (employee == null) {
                _eventChannel.send(AuthUiEvent.ShowToast(R.string.invalid_credentials))
            }
            else {
                _eventChannel.send(AuthUiEvent.Navigate(AuthDestination.MAIN))
                _eventChannel.send(AuthUiEvent.ShowToast(R.string.login_successful))
            }
        }
        catch (e: IOException) {
            _eventChannel.send(AuthUiEvent.ShowToast(R.string.unexpected_error))
        }
    }
}
