package dev.amal.passwordmanager.feature_search.presentation

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

    fun onEvent(event: SearchItemEvent) {
        when (event) {
            is SearchItemEvent.OnSearchQueryChange -> {

            }
        }
    }
}