package dev.amal.passwordmanager.feature_add_card

sealed class AddCardFormEvent {

    data class TitleChanged(val title: String) : AddCardFormEvent()
    data class CardHolderNameChanged(val cardHolderName: String) : AddCardFormEvent()
    data class CardNumberChanged(val cardNumber: String) : AddCardFormEvent()
    data class ExpirationDateChanged(val expirationDate: String) : AddCardFormEvent()
    data class CVVChanged(val CVV: String) : AddCardFormEvent()
    data class CardPinChanged(val cardPin: String) : AddCardFormEvent()
    data class ZIPChanged(val ZIP: String) : AddCardFormEvent()

    object Submit : AddCardFormEvent()
}