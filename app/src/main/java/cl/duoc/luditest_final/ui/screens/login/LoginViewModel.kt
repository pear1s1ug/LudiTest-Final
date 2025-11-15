package cl.duoc.luditest_final.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.luditest_final.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    // ✅ NUEVO: Login con email y contraseña
    fun loginWithPassword(email: String, password: String) {
        _loginState.value = LoginState(isLoading = true)

        viewModelScope.launch {
            try {
                val success = userRepository.loginWithPassword(email, password)
                if (success) {
                    _loginState.value = LoginState(isSuccess = true)
                } else {
                    _loginState.value = LoginState(error = "Email o contraseña incorrectos")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState(error = "Error al iniciar sesión: ${e.message}")
            }
        }
    }

    fun loginAsGuest() {
        _loginState.value = LoginState(isLoading = true)

        viewModelScope.launch {
            try {
                userRepository.login(
                    userId = "guest_${System.currentTimeMillis()}",
                    userName = "Invitado",
                    email = null
                )
                _loginState.value = LoginState(isSuccess = true)
            } catch (e: Exception) {
                _loginState.value = LoginState(error = "Error al entrar como invitado: ${e.message}")
            }
        }
    }

    fun clearError() {
        _loginState.value = LoginState()
    }
}