package dev.amal.passwordmanager.feature_details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(
    showSnackBar: (String) -> Unit,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state = detailsViewModel.state.value

    state.password?.let {
        DetailsContent(
            showSnackBar = showSnackBar,
            navController = navController,
            selectedPassword = state.password
        )
    }

}