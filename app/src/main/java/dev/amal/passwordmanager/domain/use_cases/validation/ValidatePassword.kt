package dev.amal.passwordmanager.domain.use_cases.validation

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        return ValidationResult(successful = true)
    }
}