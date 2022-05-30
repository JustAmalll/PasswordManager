package dev.amal.passwordmanager.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.ui.theme.Gray
import dev.amal.passwordmanager.ui.theme.MainGray

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
        shape = RoundedCornerShape(size = 22.dp)
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
            Text(text = "Search", color = Gray)
        }
    }
}