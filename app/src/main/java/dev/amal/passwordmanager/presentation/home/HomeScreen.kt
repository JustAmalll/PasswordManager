package dev.amal.passwordmanager.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.StandardToolbar
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.presentation.common.ListContent
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val allItems by sharedViewModel.allItems.collectAsState()

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
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            SearchButton(navController = navController)
            ListContent(items = allItems, navController = navController)
        }
    }
}

@Composable
fun SearchButton(
    navController: NavHostController
) {
    Button(
        onClick = {
            navController.navigate(Screen.SearchScreen.route)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.LightGray,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Search")
        }
    }
}

