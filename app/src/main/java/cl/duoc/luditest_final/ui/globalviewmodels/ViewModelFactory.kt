package cl.duoc.luditest_final.ui.globalviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duoc.luditest_final.data.repository.UserRepository
import cl.duoc.luditest_final.ui.screens.home.HomeViewModel
import cl.duoc.luditest_final.ui.screens.login.LoginViewModel
import cl.duoc.luditest_final.ui.screens.quiz.QuizViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> {
                QuizViewModel() as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}