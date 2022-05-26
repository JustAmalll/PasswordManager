package dev.amal.passwordmanager.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.data.repositories.PasswordRepository
import dev.amal.passwordmanager.utils.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: PasswordRepository,
): ViewModel() {

    val title: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val website: MutableState<String> = mutableStateOf("")

    private val _allItems =
        MutableStateFlow<RequestState<List<Password>>>(RequestState.Idle)
    val allItems: StateFlow<RequestState<List<Password>>> = _allItems

    init {
        getAllItems()
    }

    fun addItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val password = Password(
                title = title.value,
                email = email.value,
                password = password.value,
                website = website.value,
            )
            repository.addItem(password = password)
        }
    }

    private fun getAllItems() {
        _allItems.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllItems.collect {
                    _allItems.value = RequestState.Success(data = it)
                }
            }
        } catch (e: Exception) {
            _allItems.value = RequestState.Error(e)
        }
    }

    fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val password = Password(
                title = title.value,
                email = email.value,
                password = password.value,
                website = website.value,
            )
            repository.deleteTask(password = password)
        }
    }

    fun validateFields(): Boolean =
        title.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()
}