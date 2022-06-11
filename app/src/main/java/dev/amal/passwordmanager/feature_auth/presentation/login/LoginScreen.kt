package dev.amal.passwordmanager.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.feature_auth.domain.models.AuthResult
import dev.amal.passwordmanager.feature_auth.presentation.AuthUiEvent
import dev.amal.passwordmanager.feature_auth.presentation.AuthViewModel
import dev.amal.passwordmanager.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit
) {
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.HomeScreen.route)
                }
                is AuthResult.Unauthorized -> {}
                is AuthResult.UnknownError -> {
                    showSnackBar("An unknown error occurred")
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.signInUsername,
                onValueChange = {
                    viewModel.onEvent(AuthUiEvent.SignInUsernameChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Username")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.signInPassword,
                onValueChange = {
                    viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onEvent(AuthUiEvent.SignIn)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Sign in", color = MaterialTheme.colors.onPrimary)
            }
        }
        Text(
            text = buildAnnotatedString {
                append("Don't have an account yet? ")
                withStyle(
                    style = SpanStyle(color = MaterialTheme.colors.primary)
                ) {
                    append("Sign up!")
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable { navController.navigate(Screen.RegisterScreen.route) }
        )
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}