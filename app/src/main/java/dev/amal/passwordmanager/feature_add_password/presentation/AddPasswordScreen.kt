package dev.amal.passwordmanager.feature_add_password.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.StandardToolbar
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.ui.theme.Green
import dev.amal.passwordmanager.ui.theme.MainGray
import kotlinx.coroutines.flow.collect

@Composable
fun AddPasswordScreen(
    showSnackBar: (String) -> Unit,
    navController: NavHostController,
    addPasswordViewModel: AddPasswordViewModel = hiltViewModel()
) {

    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val state = addPasswordViewModel.state

    val snackBarHostState = rememberScaffoldState()

    LaunchedEffect(key1 = context) {
        addPasswordViewModel.result.collect { result ->
            when (result) {
                is Resource.Success -> {
                    showSnackBar("Password added successfully")
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
                        addPasswordViewModel.onEvent(AddPasswordFormEvent.Submit)
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
                        text = "Add Password",
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

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.title,
                    onValueChange = {
                        addPasswordViewModel.onEvent(AddPasswordFormEvent.TitleChanged(it))
                    },
                    singleLine = true,
                    isError = state.titleError != null,
                    label = { Text(text = "Title") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    textStyle = MaterialTheme.typography.h3
                )
                if (state.titleError != null) {
                    Text(
                        text = state.titleError,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Password Details",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = {
                        addPasswordViewModel.onEvent(AddPasswordFormEvent.EmailChanged(it))
                    },
                    singleLine = true,
                    isError = state.emailError != null,
                    label = { Text(text = "Email or Username") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    textStyle = MaterialTheme.typography.h3
                )
                if (state.emailError != null) {
                    Text(
                        text = state.emailError,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = {
                        addPasswordViewModel.onEvent(AddPasswordFormEvent.PasswordChanged(it))
                    },
                    isError = state.passwordError != null,
                    label = { Text(text = "Password") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h4,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (showPassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        if (state.password.isNotEmpty()) {
                            val image =
                                if (showPassword) painterResource(id = R.drawable.ic_visibility_off)
                                else painterResource(id = R.drawable.ic_visibility)

                            val description = if (showPassword) "Hide password" else "Show password"

                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(painter = image, description)
                            }
                        }
                    }
                )
                if (state.passwordError != null) {
                    Text(
                        text = state.passwordError,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    Text(text = "Generate Password")
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.website,
                    onValueChange = {
                        addPasswordViewModel.onEvent(AddPasswordFormEvent.WebsiteChanged(it))
                    },
                    label = { Text(text = "Website or App Name") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h3,
                    isError = state.websiteError != null
                )
                if (state.websiteError != null) {
                    Text(
                        text = state.websiteError,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    )
}