package dev.amal.passwordmanager.presentation.add_password

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.StandardToolbar
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel
import dev.amal.passwordmanager.ui.theme.BackGroundColor
import dev.amal.passwordmanager.ui.theme.Green
import dev.amal.passwordmanager.ui.theme.MainGray

@Composable
fun AddPasswordScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(topBar = {
        StandardToolbar(
            onCloseClicked = { navController.popBackStack() },
            showBackArrow = true,
            navActions = {
                IconButton(onClick = {
                    if (sharedViewModel.validateFields()) {
                        sharedViewModel.addItem()
                        navController.navigate(Screen.HomeScreen.route)
                    } else {
                        Toast.makeText(context, "Validate fields.", Toast.LENGTH_LONG).show()
                    }
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
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGray)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = sharedViewModel.title.value,
                onValueChange = { sharedViewModel.title.value = it },
                label = { Text(text = "Title") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
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
                value = sharedViewModel.email.value,
                onValueChange = { sharedViewModel.email.value = it },
                label = { Text(text = "Email or Username") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = sharedViewModel.password.value,
                onValueChange = { sharedViewModel.password.value = it },
                label = { Text(text = "Password") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    if (sharedViewModel.password.value.isNotEmpty()) {
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
                value = sharedViewModel.website.value,
                onValueChange = { sharedViewModel.website.value = it },
                label = { Text(text = "Website or App Name") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
        }
    }
}