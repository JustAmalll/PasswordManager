package dev.amal.passwordmanager.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.StandardToolbar
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.presentation.common.ListContent
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel
import dev.amal.passwordmanager.ui.theme.Gray
import dev.amal.passwordmanager.ui.theme.MainGray
import kotlinx.coroutines.launch

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
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                SearchButton(navController = navController)
                ListContent(items = allItems, navController = navController)
            }
        }
    )
}

@Composable
fun SearchButton(
    navController: NavHostController
) {
    Button(
        modifier = Modifier
            .padding(top = 6.dp)
            .padding(horizontal = 12.dp),
        onClick = {
            navController.navigate(Screen.SearchScreen.route)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MainGray,
            contentColor = Gray
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