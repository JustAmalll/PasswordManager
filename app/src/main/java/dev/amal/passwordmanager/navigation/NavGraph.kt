package dev.amal.passwordmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.amal.passwordmanager.presentation.add_password.AddPasswordScreen
import dev.amal.passwordmanager.presentation.details_screen.DetailsScreen
import dev.amal.passwordmanager.presentation.home.HomeScreen
import dev.amal.passwordmanager.presentation.search_screen.SearchScreen
import dev.amal.passwordmanager.utils.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.AddPassword.route) {
            AddPasswordScreen(navController = navController)
        }
    }
}