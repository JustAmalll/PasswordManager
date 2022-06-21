package dev.amal.passwordmanager.feature_add_card.data.remote.request

data class CardRequest(
    val title: String,
    val cardHolderName: String,
    val cardNumber: String,
    val expirationDate: String,
    val CVV: String,
    val cardPin: String,
    val ZIP: String
)