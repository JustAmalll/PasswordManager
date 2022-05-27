package dev.amal.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.amal.passwordmanager.navigation.SetupNavGraph
import dev.amal.passwordmanager.ui.theme.PasswordManagerTheme
import dev.amal.passwordmanager.utils.SnackBarAppState
import dev.amal.passwordmanager.utils.rememberSnackBarAppState

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = White, darkIcons = useDarkIcons
                    )
                }

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
