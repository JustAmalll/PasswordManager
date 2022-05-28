package dev.amal.passwordmanager.presentation.home.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import dev.amal.passwordmanager.presentation.viewmodel.SharedViewModel
import dev.amal.passwordmanager.utils.copyText
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import dev.amal.passwordmanager.utils.launchWebSite

@ExperimentalMaterialApi
@Composable
fun ModalBottomSheetLayout(
    sharedViewModel: SharedViewModel,
    showSnackBar: (String) -> Unit,
    modalBottomSheetState: ModalBottomSheetState,
    content: @Composable () -> Unit
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            BottomSheetContent(
                selectedItem = sharedViewModel.selectedItem,
                onLaunchWebsite = {
                    launchWebSite(it, context)
                },
                onCopyEmail = {
                    copyText(text = it, context)
                    showSnackBar("Email/Username copied to clipboard")
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                },
                onCopyPassword = {
                    copyText(text = it, context)
                    showSnackBar("Password copied to clipboard")
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                },
                onEdit = {},
                onShare = {},
                onDelete = {
                    sharedViewModel.deleteTask(it)
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                }
            )
        }
    ) {
        content()
    }
}

