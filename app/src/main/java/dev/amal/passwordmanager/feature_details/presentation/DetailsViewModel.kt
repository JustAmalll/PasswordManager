package dev.amal.passwordmanager.feature_details.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.use_cases.PasswordUseCases
import dev.amal.passwordmanager.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val passwordUseCases: PasswordUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state = mutableStateOf(DetailState())

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(Constants.DETAILS_ARGUMENT_KEY)?.let { itemId ->
                loadPasswordDetails(itemId)
            }
        }
    }

    private fun loadPasswordDetails(passwordId: String) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            when (val result = passwordUseCases.getPasswordDetailsUseCase(passwordId)) {
                is Resource.Success -> {
                    state.value = state.value.copy(
                        passwordItem = result.data, isLoading = false
                    )
                }
                is Resource.Error -> {
                    state.value = state.value.copy(isLoading = false)
                    // show error SnackBar
                }
            }
        }
    }
}