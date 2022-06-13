package dev.amal.passwordmanager.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.amal.passwordmanager.feature_add_password.presentation.AddPasswordScreen
import dev.amal.passwordmanager.feature_auth.presentation.login.LoginScreen
import dev.amal.passwordmanager.feature_auth.presentation.register.RegisterScreen
import dev.amal.passwordmanager.feature_details.presentation.DetailsScreen
import dev.amal.passwordmanager.feature_home.presentation.HomeScreen
import dev.amal.passwordmanager.feature_search.presentation.SearchScreen
import dev.amal.passwordmanager.utils.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    showSnackBar: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, showSnackBar = showSnackBar)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController, showSnackBar = showSnackBar)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, showSnackBar = showSnackBar)
        }
        composable(
            route = Screen.DetailsScreen.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
            DetailsScreen(navController = navController, showSnackBar = showSnackBar)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController, showSnackBar = showSnackBar)
        }
        composable(route = Screen.AddPassword.route) {
            AddPasswordScreen(navController = navController, showSnackBar = showSnackBar)
        }
    }
}