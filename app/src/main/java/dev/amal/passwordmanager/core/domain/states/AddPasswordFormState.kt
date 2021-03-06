package dev.amal.passwordmanager.core.domain.states

data class AddPasswordFormState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val website: String = "",
    val websiteError: String? = null,
)