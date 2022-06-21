package dev.amal.passwordmanager.navigation

import dev.amal.passwordmanager.utils.Constants

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object DetailsScreen : Screen("details_screen/{${Constants.DETAILS_ARGUMENT_KEY}}") {
        fun passItemId(itemId: String): String = "details_screen/$itemId"
    }
    object SearchScreen : Screen("search_screen")
    object AddPassword : Screen("add_password_screen")
    object AddCard : Screen("add_card_screen")
}