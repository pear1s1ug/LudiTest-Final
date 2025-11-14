package cl.duoc.luditest_final.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Disclaimer : Routes("disclaimer")
    object Quiz : Routes("quiz")
    object Result : Routes("results")
    object GameDetail : Routes("gamedetail")
    object UserProfile : Routes("userprofile")
    object Wishlist : Routes("wishlist")
    object Recommended : Routes("recommended")
}