package dev.amal.passwordmanager.feature_details.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.use_case.PasswordUseCases
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
        savedStateHandle.get<String>(Constants.DETAILS_ARGUMENT_KEY)?.let { itemId ->
            loadPasswordDetails(itemId)
        }
    }

    private fun loadPasswordDetails(passwordId: String) {
        viewModelScope.launch {
            state.value = state.value.copy(
                isLoading = true
            )
            val result = passwordUseCases.getPasswordDetailsUseCase(passwordId)
            when (result) {
                is Resource.Success -> {
                    state.value = state.value.copy(
                        password = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    state.value = state.value.copy(
                        isLoading = false
                    )
                    // showError SnackBar
                }
            }
        }
    }
}