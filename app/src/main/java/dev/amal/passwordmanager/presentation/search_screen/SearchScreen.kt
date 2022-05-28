package dev.amal.passwordmanager.presentation.search_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.presentation.common.ListContent
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel
import dev.amal.passwordmanager.presentation.home.components.ModalBottomSheetLayout

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
) {

    val searchedTasks by searchViewModel.searchedTasks.collectAsState()

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        sharedViewModel = sharedViewModel,
        showSnackBar = showSnackBar,
        modalBottomSheetState = modalBottomSheetState
    ) {
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
}