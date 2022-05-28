package dev.amal.passwordmanager.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.data.repositories.PasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: PasswordRepository
): ViewModel() {

    var selectedItem by mutableStateOf(
        Password(
            id = 0,
            title = "Title",
            email = "Email",
            password = "Password",
            website = "Website"
        )
    )
        private set

    fun onSelectedItem(password: Password) {
        selectedItem = password
    }

    fun deleteTask(password: Password) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(password = password)
        }
    }

}