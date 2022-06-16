package dev.amal.passwordmanager.feature_search.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.core.presentation.common.Item
import dev.amal.passwordmanager.core.presentation.components.ModalBottomSheetLayout
import dev.amal.passwordmanager.core.sharedViewModel.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit
) {

    val state = searchViewModel.searchState.value

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        showSnackBar = showSnackBar,
        modalBottomSheetState = modalBottomSheetState,
        selectedItem = sharedViewModel.selectedItem.value
    ) {
        Scaffold(
            topBar = {
                SearchTopBar(
                    searchViewModel = searchViewModel,
                    navController = navController
                )
            },
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.passwordItems) { password ->
                        Item(
                            item = password,
                            navController = navController,
                            modalBottomSheetState = modalBottomSheetState,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }
        )
    }
}