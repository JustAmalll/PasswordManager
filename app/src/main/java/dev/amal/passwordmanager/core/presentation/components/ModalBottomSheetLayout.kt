package dev.amal.passwordmanager.core.presentation.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.core.presentation.components.BottomSheetContent
import dev.amal.passwordmanager.utils.copyText
import kotlinx.coroutines.launch
import dev.amal.passwordmanager.utils.launchWebSite

@ExperimentalMaterialApi
@Composable
fun ModalBottomSheetLayout(
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
                selectedItem = Password(id = "", "", "", "", ""),
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

