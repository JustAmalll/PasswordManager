package dev.amal.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.amal.passwordmanager.navigation.SetupNavGraph
import dev.amal.passwordmanager.ui.theme.PasswordManagerTheme
import dev.amal.passwordmanager.utils.SnackBarAppState
import dev.amal.passwordmanager.utils.rememberSnackBarAppState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme {

                val appState: SnackBarAppState = rememberSnackBarAppState()
                navController = rememberNavController()

                Scaffold(
                    scaffoldState = appState.scaffoldState
                ) {
                    SetupNavGraph(
                        navController = navController,
                        showSnackBar = { message ->
                            appState.showSnackBar(message = message, duration = SnackbarDuration.Short)
                        }
                    )
                }
            }
        }
    }
}
