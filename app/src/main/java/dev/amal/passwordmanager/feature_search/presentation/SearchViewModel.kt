package dev.amal.passwordmanager.feature_search.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.core.domain.states.StandardTextFieldState
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.use_cases.PasswordUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val passwordUseCases: PasswordUseCases
) : ViewModel() {

    private val _searchFieldState = mutableStateOf(StandardTextFieldState())
    val searchFieldState: State<StandardTextFieldState> = _searchFieldState

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                searchPassword(event.query)
            }
        }
    }

    private fun searchPassword(query: String) {
        _searchFieldState.value = searchFieldState.value.copy(text = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300L)
            _searchState.value = searchState.value.copy(isLoading = true)
            when (val result = passwordUseCases.searchPasswordUseCase(query)) {
                is Resource.Success -> {
                    _searchState.value = searchState.value.copy(
                        passwordItems = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _searchFieldState.value = searchFieldState.value.copy(
//                        error = SearchError(
//                            message = result.uiText ?: UiText.unknownError()
//                        )
                    )
                    _searchState.value = searchState.value.copy(isLoading = false)
                }
            }
        }
    }

    // For BottomSheet
    val selectedItem: MutableState<PasswordItem> =
        mutableStateOf(PasswordItem(id = "0", "Title", "Email", "pwd", "web"))

    fun onSelectedItem(passwordItem: PasswordItem) {
        selectedItem.value = passwordItem
    }
}