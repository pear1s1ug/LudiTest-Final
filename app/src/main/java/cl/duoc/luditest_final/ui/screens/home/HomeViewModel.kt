package cl.duoc.luditest_final.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.luditest_final.data.model.User
import cl.duoc.luditest_final.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UserState(
    val user: User? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    fun loadUserState() {
        viewModelScope.launch {
            _userState.value = _userState.value.copy(isLoading = true)

            userRepository.isLoggedIn.collect { isLoggedIn ->
                val user = if (isLoggedIn) userRepository.getCurrentUser() else null
                _userState.value = UserState(
                    user = user,
                    isLoggedIn = isLoggedIn,
                    isLoading = false
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}