package dev.amal.passwordmanager.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object Details : Screen("details_screen/{itemId}") {
        fun passItemId(itemId: Int): String = "details_screen/$itemId"
    }
    object SearchScreen : Screen("search_screen")
    object AddPassword : Screen("add_password_screen")
}