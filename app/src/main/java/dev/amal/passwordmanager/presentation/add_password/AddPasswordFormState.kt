package dev.amal.passwordmanager.presentation.add_password

data class AddPasswordFormState(
    val title: String = "",
    val titleError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val website: String = "",
    val websiteError: String? = null,
)