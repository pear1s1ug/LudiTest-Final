package cl.duoc.luditest_final.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import cl.duoc.luditest_final.ui.screens.home.HomeScreen
import cl.duoc.luditest_final.ui.screens.disclaimer.DisclaimerScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import cl.duoc.luditest_final.ui.screens.login.LoginScreen
import cl.duoc.luditest_final.ui.screens.quiz.QuizScreen

@Composable
fun AppNavigation(
    viewModelFactory: ViewModelProvider.Factory
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            val homeViewModel: cl.duoc.luditest_final.ui.screens.home.HomeViewModel =
                viewModel(factory = viewModelFactory)

            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToDisclaimer = {
                    navController.navigate(Routes.Disclaimer.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Routes.UserProfile.route)
                },
                onNavigateToWishlist = {
                    navController.navigate(Routes.Wishlist.route)
                },
                onNavigateToRecommended = {
                    navController.navigate(Routes.Recommended.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route)
                }
            )
        }

        composable(Routes.Disclaimer.route) {
            DisclaimerScreen(
                onAccept = {
                    navController.navigate(Routes.Quiz.route)
                },
                onDecline = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.Quiz.route) {
            val quizViewModel: cl.duoc.luditest_final.ui.screens.quiz.QuizViewModel =
                viewModel(factory = viewModelFactory)

            QuizScreen(
                viewModel = quizViewModel,
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Quiz.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Login.route) {
            val loginViewModel: cl.duoc.luditest_final.ui.screens.login.LoginViewModel =
                viewModel(factory = viewModelFactory)

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.popBackStack(Routes.Home.route, inclusive = false)
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.Login.route)
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.Result.route) {
            Text(
                "Resultados - Por implementar",
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(Routes.Recommended.route) {
            Text(
                "Todos los Juegos Recomendados - Por implementar",
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(Routes.GameDetail.route) {
            Text(
                "Detalle del Juego - Por implementar",
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(Routes.UserProfile.route) {
            Text(
                "Perfil de Usuario - Por implementar",
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(Routes.Wishlist.route) {
            Text(
                "Wishlist - Por implementar",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}