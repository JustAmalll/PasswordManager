package dev.amal.passwordmanager.presentation.search_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.presentation.common.ListContent
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val searchedTasks by searchViewModel.searchedTasks.collectAsState()

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    Scaffold(
        topBar = {
            SearchTopBar(
                searchViewModel = searchViewModel,
                navController = navController
            )
        },
        content = {
            ListContent(
                items = searchedTasks,
                navController = navController,
                modalBottomSheetState = modalBottomSheetState,
                sharedViewModel = sharedViewModel
            )
        }
    )
}