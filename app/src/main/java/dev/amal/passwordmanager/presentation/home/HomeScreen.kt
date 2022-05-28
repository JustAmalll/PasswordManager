package dev.amal.passwordmanager.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.StandardToolbar
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.presentation.common.ListContent
import dev.amal.passwordmanager.presentation.home.components.SearchButton
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel
import dev.amal.passwordmanager.presentation.home.components.ModalBottomSheetLayout

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
) {

    val allItems by homeViewModel.allItems.collectAsState()

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
                        items = allItems,
                        navController = navController,
                        modalBottomSheetState = modalBottomSheetState,
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        )
    }
}

