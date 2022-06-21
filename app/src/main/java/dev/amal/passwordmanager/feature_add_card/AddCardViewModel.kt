package dev.amal.passwordmanager.feature_add_card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.domain.states.AddCardFormState
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_card.domain.use_cases.CardUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val useCases: CardUseCases
) : ViewModel() {

    var state by mutableStateOf(AddCardFormState())

    private val resultChannel = Channel<Resource<Unit>>()
    val result = resultChannel.receiveAsFlow()

    fun onEvent(event: AddCardFormEvent) {
        when (event) {
            is AddCardFormEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }
            is AddCardFormEvent.CardHolderNameChanged -> {
                state = state.copy(cardHolderName = event.cardHolderName)
            }
            is AddCardFormEvent.CardNumberChanged -> {
                state = state.copy(cardNumber = event.cardNumber)
            }
            is AddCardFormEvent.ExpirationDateChanged -> {
                state = state.copy(expirationDate = event.expirationDate)
            }
            is AddCardFormEvent.CVVChanged -> {
                state = state.copy(CVV = event.CVV)
            }
            is AddCardFormEvent.CardPinChanged -> {
                state = state.copy(cardPin = event.cardPin)
            }
            is AddCardFormEvent.ZIPChanged -> {
                state = state.copy(ZIP = event.ZIP)
            }
            is AddCardFormEvent.Submit -> {
                viewModelScope.launch {
                    state = state.copy(isLoading = true)
                    val result = useCases.addCardUseCase(
                        title = state.title,
                        cardHolderName = state.cardHolderName,
                        cardNumber = state.cardNumber,
                        expirationDate = state.expirationDate,
                        CVV = state.CVV,
                        cardPin = state.cardPin,
                        ZIP = state.ZIP
                    )
                    resultChannel.send(result)
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}