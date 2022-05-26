package dev.amal.passwordmanager.domain.use_cases.validation

class ValidateTitle {

    fun execute(title: String): ValidationResult {
        if (title.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The title can't be blank"
            )
        }
        return ValidationResult(successful = true)
    }
}