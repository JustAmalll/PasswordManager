package dev.amal.passwordmanager.feature_auth.presentation.register

import android.widget.Toast
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
import kotlinx.coroutines.flow.collect

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.HomeScreen.route)
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You're not authorized",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
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
            Text(text = "Sign Up", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.signUpUsername,
                onValueChange = {
                    viewModel.onEvent(AuthUiEvent.SignUpUsernameChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Username")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.signUpPassword,
                onValueChange = {
                    viewModel.onEvent(AuthUiEvent.SignUpPasswordChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onEvent(AuthUiEvent.SignUp)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Sign up")
            }
        }
        Text(
            text = buildAnnotatedString {
                append("Already have an account? ")
                withStyle(
                    style = SpanStyle(color = MaterialTheme.colors.primary)
                ) {
                    append("Sign in!")
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable { navController.navigate(Screen.LoginScreen.route) }
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