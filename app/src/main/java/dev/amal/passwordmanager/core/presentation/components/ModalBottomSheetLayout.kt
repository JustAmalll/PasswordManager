package dev.amal.passwordmanager.core.presentation.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.utils.copyText
import dev.amal.passwordmanager.utils.launchWebSite
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ModalBottomSheetLayout(
    selectedItem: PasswordItem,
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
                selectedItem = selectedItem,
                onLaunchWebsite = { launchWebSite(it, context) },
                onCopyEmail = {
                    copyText(text = it, context)
                    showSnackBar("Email/Username copied to clipboard")
                    scope.launch { modalBottomSheetState.hide() }
                },
                onCopyPassword = {
                    copyText(text = it, context)
                    showSnackBar("Password copied to clipboard")
                    scope.launch { modalBottomSheetState.hide() }
                },
                onEdit = {},
                onShare = {},
                onDelete = {
                    scope.launch { modalBottomSheetState.hide() }
                }
            )
        }
    ) {
        content()
    }
}

