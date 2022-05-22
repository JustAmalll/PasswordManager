package dev.amal.passwordmanager.presentation.search_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.presentation.common.ListContent

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchedTasks by searchViewModel.searchedTasks.collectAsState()

    Scaffold(
        topBar = {
            SearchTopBar(
                searchViewModel = searchViewModel,
                navController = navController
            )
        },
        content = {
            ListContent(items = searchedTasks, navController = navController)
        }
    )
}