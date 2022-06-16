package dev.amal.passwordmanager.feature_search.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import dev.amal.passwordmanager.core.presentation.ui.theme.Gray
import dev.amal.passwordmanager.core.presentation.ui.theme.MainGray

@Composable
fun SearchTopBar(
    searchViewModel: SearchViewModel,
    navController: NavHostController
) {

    val state = searchViewModel.searchFieldState.value

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp),
        value = state.text,
        onValueChange = {
            searchViewModel.onEvent(SearchEvent.OnSearchQueryChange(it))
        },
        shape = RoundedCornerShape(30.dp),
        placeholder = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "Search here ...",
                color = Gray
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
            if (state.text.isNotEmpty()) {
                IconButton(
                    onClick = { state.text = "" }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear",
                        tint = Color.Black
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MainGray,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}
