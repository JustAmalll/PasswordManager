package dev.amal.passwordmanager.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.data.models.Password

class SharedViewModel: ViewModel() {

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

}