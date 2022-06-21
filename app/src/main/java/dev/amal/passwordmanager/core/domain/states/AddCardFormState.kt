package dev.amal.passwordmanager.core.domain.states

data class AddCardFormState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String? = null,
    val cardHolderName: String = "",
    val cardNumber: String = "",
    val cardNumberError: String? = null,
    val expirationDate: String = "",
    val CVV: String = "",
    val cardPin: String = "",
    val ZIP: String = "",
)