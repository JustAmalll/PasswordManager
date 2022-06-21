package dev.amal.passwordmanager.feature_home.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.feature_add_password.domain.use_cases.PasswordUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    passwordUseCases: PasswordUseCases
) : ViewModel() {

    val passwords = passwordUseCases.getPasswordsUseCase().cachedIn(viewModelScope)

    // For BottomSheet
    val selectedItem: MutableState<PasswordItem> =
        mutableStateOf(PasswordItem(id = "0", "Title", "Email", "pwd", "web"))

    fun onSelectedItem(passwordItem: PasswordItem) {
        selectedItem.value = passwordItem
    }
}