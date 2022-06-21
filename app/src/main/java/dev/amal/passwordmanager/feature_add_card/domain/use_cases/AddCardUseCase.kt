package dev.amal.passwordmanager.feature_add_card.domain.use_cases

import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import dev.amal.passwordmanager.core.utils.UiText
import dev.amal.passwordmanager.feature_add_card.domain.repository.CardRepository

class AddCardUseCase(
    private val repository: CardRepository
) {

    suspend operator fun invoke(
        title: String,
        cardHolderName: String,
        cardNumber: String,
        expirationDate: String,
        CVV: String,
        cardPin: String,
        ZIP: String
    ): SimpleResource {
        if (title.isBlank()) return Resource.Error(
            uiText = UiText.StringResource(R.string.error_title_blank)
        )
        if (cardNumber.isBlank()) return Resource.Error(
            uiText = UiText.StringResource(R.string.error_card_number_blank)
        )
        return repository.addCard(
            title, cardHolderName, cardNumber, expirationDate, CVV, cardPin, ZIP
        )
    }
}