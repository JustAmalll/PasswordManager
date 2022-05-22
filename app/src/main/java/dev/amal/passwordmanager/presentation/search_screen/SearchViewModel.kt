package dev.amal.passwordmanager.presentation.search_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.data.repositories.PasswordRepository
import dev.amal.passwordmanager.utils.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PasswordRepository
): ViewModel() {

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _searchTasks =
        MutableStateFlow<RequestState<List<Password>>>(RequestState.Idle)
    val searchedTasks: StateFlow<RequestState<List<Password>>> = _searchTasks

    fun searchDatabase(searchQuery: String) {
        _searchTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.searchDatabase(searchQuery = "%$searchQuery%")
                    .collect { searchedTasks ->
                        _searchTasks.value = RequestState.Success(searchedTasks)
                    }
            }
        } catch (e: Exception) {
            _searchTasks.value = RequestState.Error(e)
        }
    }
}