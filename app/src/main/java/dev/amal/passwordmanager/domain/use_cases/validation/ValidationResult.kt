package dev.amal.passwordmanager.domain.use_cases.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)