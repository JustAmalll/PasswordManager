package dev.amal.passwordmanager.feature_search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.items
import dev.amal.passwordmanager.core.presentation.common.EmptyContent
import dev.amal.passwordmanager.core.presentation.common.Item
import dev.amal.passwordmanager.core.presentation.components.ModalBottomSheetLayout

@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit
) {

    val state = searchViewModel.searchState.value

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        showSnackBar = showSnackBar,
        modalBottomSheetState = modalBottomSheetState,
        selectedItem = searchViewModel.selectedItem.value
    ) {
        Scaffold(
            topBar = {
                SearchTopBar(
                    searchViewModel = searchViewModel,
                    navController = navController
                )
            },
            content = {
                if (state.passwordItems.isEmpty())
                    EmptyContent()
                else {
                    LazyColumn(
                        modifier = Modifier.padding(top = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(state.passwordItems) { password ->
                            println(password.id + " from search screen")
                            Item(
                                item = password,
                                navController = navController,
                                modalBottomSheetState = modalBottomSheetState,
                                selectedItem = { searchViewModel.onSelectedItem(it) }
                            )
                        }
                    }
                }
            }
        )
    }
}