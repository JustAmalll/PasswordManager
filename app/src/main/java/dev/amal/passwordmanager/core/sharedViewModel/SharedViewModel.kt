package dev.amal.passwordmanager.core.sharedViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    val selectedItem: MutableState<PasswordItem> =
        mutableStateOf(PasswordItem(id = "0", "Title", "Email", "pwd", "web"))

    fun onSelectedItem(passwordItem: PasswordItem) {
        selectedItem.value = passwordItem
    }
}