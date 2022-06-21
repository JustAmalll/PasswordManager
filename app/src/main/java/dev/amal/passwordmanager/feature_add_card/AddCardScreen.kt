package dev.amal.passwordmanager.feature_add_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.core.presentation.components.StandardTextField
import dev.amal.passwordmanager.core.presentation.components.StandardToolbar
import dev.amal.passwordmanager.core.presentation.ui.theme.Green
import dev.amal.passwordmanager.core.presentation.ui.theme.MainGray
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.presentation.AddPasswordFormEvent
import dev.amal.passwordmanager.navigation.Screen

@Composable
fun AddCardScreen(
    showSnackBar: (String) -> Unit,
    navController: NavHostController,
    addCardViewModel: AddCardViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = addCardViewModel.state

    val snackBarHostState = rememberScaffoldState()

    LaunchedEffect(key1 = context) {
        addCardViewModel.result.collect { result ->
            when (result) {
                is Resource.Success -> {
                    showSnackBar("Card added successfully")
                    navController.navigate(Screen.HomeScreen.route)
                }
                is Resource.Error -> {
                    showSnackBar("Error")
                }
            }
        }
    }

    Scaffold(
        scaffoldState = snackBarHostState,
        topBar = {
            StandardToolbar(
                onCloseClicked = { navController.popBackStack() },
                showBackArrow = true,
                navActions = {
                    IconButton(onClick = {
                        addCardViewModel.onEvent(AddCardFormEvent.Submit)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save changes",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                title = {
                    Text(
                        text = "Add Credit Card",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainGray)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                StandardTextField(
                    text = state.title,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.TitleChanged(it))
                    },
                    label = "Title",
                    error = state.titleError ?: ""
                )
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Card Details",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        showSnackBar("Logic not implemented yet!")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    Text(text = "Scan Card With Camera")
                }
                Spacer(modifier = Modifier.height(16.dp))
                StandardTextField(
                    text = state.cardHolderName,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.CardHolderNameChanged(it))
                    },
                    label = "Cardholder Name",
                )
                Spacer(modifier = Modifier.height(16.dp))
                StandardTextField(
                    text = state.cardNumber,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.CardNumberChanged(it))
                    },
                    label = "Card Number",
                    error = state.cardNumberError ?: ""
                )
                Spacer(modifier = Modifier.height(16.dp))
                StandardTextField(
                    text = state.expirationDate,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.ExpirationDateChanged(it))
                    },
                    label = "Expiration Date"
                )
                Spacer(modifier = Modifier.height(16.dp))
                StandardTextField(
                    text = state.CVV,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.CVVChanged(it))
                    },
                    label = "CVV"
                ) 
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.cardPin,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.CardPinChanged(it))
                    },
                    label = { Text(text = "Card PIN") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h3
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.ZIP,
                    onValueChange = {
                        addCardViewModel.onEvent(AddCardFormEvent.ZIPChanged(it))
                    },
                    label = { Text(text = "ZIP or Post Code") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h3
                )
            }
        }
    )
}