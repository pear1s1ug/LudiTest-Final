package cl.duoc.luditest_final.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.luditest_final.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository // ✅ ESTE CONSTRUCTOR
) : ViewModel() {

    private val _userName = MutableStateFlow("Invitado")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _isGuest = MutableStateFlow(true)
    val isGuest: StateFlow<Boolean> = _isGuest.asStateFlow()

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId.asStateFlow()

    private val _userEmail = MutableStateFlow("No especificado")
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()

    init {
        loadUserSimple()
    }

    private fun loadUserSimple() {
        viewModelScope.launch {
            try {
                val user = userRepository.getCurrentUser()
                _userName.value = user?.name ?: "Invitado"
                _isGuest.value = user?.hasPassword != true
                _userId.value = user?.id ?: "N/A"
                _userEmail.value = user?.email ?: "No especificado"
            } catch (e: Exception) {
                _userName.value = "Usuario"
                _isGuest.value = true
                _userId.value = "Error al cargar"
                _userEmail.value = "No disponible"
            }
        }
    }
}