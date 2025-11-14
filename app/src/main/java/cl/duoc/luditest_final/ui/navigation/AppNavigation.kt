package cl.duoc.luditest_final.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import cl.duoc.luditest_final.ui.screens.home.HomeScreen
import cl.duoc.luditest_final.ui.screens.disclaimer.DisclaimerScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import cl.duoc.luditest_final.ui.screens.quiz.QuizScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
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
            QuizScreen()
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