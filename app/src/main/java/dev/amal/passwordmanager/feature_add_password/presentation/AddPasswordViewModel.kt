package dev.amal.passwordmanager.feature_add_password.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.use_case.AddPasswordUseCase
import dev.amal.passwordmanager.feature_add_password.domain.use_case.AddPasswordUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPasswordViewModel @Inject constructor(
    private val useCases: AddPasswordUseCases
) : ViewModel() {

    var state by mutableStateOf(AddPasswordFormState())

    private val resultChannel = Channel<Resource<Unit>>()
    val result = resultChannel.receiveAsFlow()

    fun onEvent(event: AddPasswordFormEvent) {
        when (event) {
            is AddPasswordFormEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }
            is AddPasswordFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is AddPasswordFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is AddPasswordFormEvent.WebsiteChanged -> {
                state = state.copy(website = event.website)
            }
            is AddPasswordFormEvent.Submit -> {
                viewModelScope.launch {
                    state = state.copy(isLoading = true)
                    val result = useCases.addPasswordUseCase(
                        title = state.title,
                        email = state.email,
                        password = state.password,
                        website = state.website
                    )
                    resultChannel.send(result)
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}
