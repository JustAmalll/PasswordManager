package dev.amal.passwordmanager.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.data.repositories.PasswordRepository
import dev.amal.passwordmanager.utils.Constants
import dev.amal.passwordmanager.utils.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PasswordRepository
) : ViewModel() {

    private val _allItems =
        MutableStateFlow<RequestState<List<Password>>>(RequestState.Idle)
    val allItems: StateFlow<RequestState<List<Password>>> = _allItems

    init {
        getAllItems()
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

    fun deleteTask(password: Password) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(password = password)
        }
    }
}