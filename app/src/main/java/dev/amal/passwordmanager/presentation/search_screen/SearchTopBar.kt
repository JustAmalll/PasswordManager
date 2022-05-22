package dev.amal.passwordmanager.presentation.search_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SearchTopBar(
    text: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    navController: NavHostController
) {
    SearchWidget(
        text = text,
        onSearchQueryChange = onSearchQueryChange,
        onSearchClicked = onSearchClicked,
        navController = navController
    )
}

@Composable
fun SearchWidget(
    text: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    navController: NavHostController
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp),
        value = text,
        onValueChange = { onSearchQueryChange(it) },
        shape = RoundedCornerShape(30.dp),
        placeholder = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "Search here ...",
                color = Color.White
            )
        },
        singleLine = true,
        leadingIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = { onSearchQueryChange("") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Clear",
                    tint = Color.Black
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClicked(text) }
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}