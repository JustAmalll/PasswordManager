package dev.amal.passwordmanager.feature_home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import dev.amal.passwordmanager.core.presentation.common.ListContent
import dev.amal.passwordmanager.core.presentation.components.ModalBottomSheetLayout
import dev.amal.passwordmanager.core.presentation.components.StandardToolbar
import dev.amal.passwordmanager.core.sharedViewModel.SharedViewModel
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.presentation.home.components.SearchButton

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit
) {

    val passwords = homeViewModel.passwords.collectAsLazyPagingItems()

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
                StandardToolbar(
                    showBackArrow = false,
                    navActions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                    },
                    title = {
                        Text(
                            text = "Password Manager",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.AddPassword.route) },
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Search")
                }
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SearchButton(navController = navController)
                    ListContent(
                        items = passwords,
                        navController = navController,
                        modalBottomSheetState = modalBottomSheetState,
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        )
    }
}
