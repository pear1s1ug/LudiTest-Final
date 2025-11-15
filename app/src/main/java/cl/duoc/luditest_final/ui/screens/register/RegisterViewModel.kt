package cl.duoc.luditest_final.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.luditest_final.data.model.UserRegistration
import cl.duoc.luditest_final.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun register(name: String, email: String, password: String) {
        _registerState.value = RegisterState(isLoading = true)

        viewModelScope.launch {
            try {
                // Validaciones básicas
                if (name.length < 2) {
                    _registerState.value = RegisterState(error = "El nombre debe tener al menos 2 caracteres")
                    return@launch
                }

                if (password.length < 6) {
                    _registerState.value = RegisterState(error = "La contraseña debe tener al menos 6 caracteres")
                    return@launch
                }

                if (!isValidEmail(email)) {
                    _registerState.value = RegisterState(error = "Por favor ingresa un email válido")
                    return@launch
                }

                // Registrar usuario
                val registration = UserRegistration(
                    name = name,
                    email = email,
                    password = password
                )

                userRepository.registerWithPassword(registration)
                _registerState.value = RegisterState(isSuccess = true)

            } catch (e: Exception) {
                _registerState.value = RegisterState(error = "Error al crear la cuenta: ${e.message}")
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun clearError() {
        _registerState.value = RegisterState()
    }
}