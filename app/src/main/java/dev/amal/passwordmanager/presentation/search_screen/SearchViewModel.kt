package dev.amal.passwordmanager.presentation.search_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.utils.RequestState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _searchTasks =
        MutableStateFlow<RequestState<List<Password>>>(RequestState.Idle)
    val searchedTasks: StateFlow<RequestState<List<Password>>> = _searchTasks

    private var searchJob: Job? = null

    fun onEvent(event: SearchItemEvent) {
        when (event) {
            is SearchItemEvent.OnSearchQueryChange -> {
                searchTextState.value = event.query
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(200L)
//                    searchDatabase()
                }
            }
        }
    }

//    private fun searchDatabase(
//        searchQuery: String = searchTextState.value
//    ) {
//        _searchTasks.value = RequestState.Loading
//        try {
//            viewModelScope.launch {
//                repository.searchDatabase(searchQuery = "%$searchQuery%")
//                    .collect { searchedTasks ->
//                        _searchTasks.value = RequestState.Success(searchedTasks)
//                    }
//            }
//        } catch (e: Exception) {
//            _searchTasks.value = RequestState.Error(e)
//        }
//    }
}