package dev.amal.passwordmanager.core.domain.states

abstract class Error

data class StandardTextFieldState(
    var text: String = "",
    val error: Error? = null
)
