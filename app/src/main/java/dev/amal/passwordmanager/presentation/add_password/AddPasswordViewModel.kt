package dev.amal.passwordmanager.presentation.add_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.domain.use_cases.UseCases
import dev.amal.passwordmanager.domain.use_cases.validation.ValidateEmail
import dev.amal.passwordmanager.domain.use_cases.validation.ValidatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPasswordViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var state by mutableStateOf(AddPasswordFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: AddPasswordFormEvent) {
        when (event) {
            is AddPasswordFormEvent.TitleChanged -> {
                state = state.copy(email = event.title)
            }
            is AddPasswordFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is AddPasswordFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is AddPasswordFormEvent.WebsiteChanged -> {
                state = state.copy(password = event.website)
            }
            is AddPasswordFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val titleResult = useCases.validateTitle.execute(state.title)
        val emailResult = useCases.validateEmail.execute(state.email)
        val passwordResult = useCases.validatePassword.execute(state.password)
        val websiteResult = useCases.validateWebsite.execute(state.website)

        val hasError = listOf(
            titleResult,
            emailResult,
            passwordResult,
            websiteResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                titleError = titleResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                websiteError = websiteResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}