package dev.amal.passwordmanager.domain.use_cases.validation

class ValidateWebsite {

    fun execute(website: String): ValidationResult {
        if (website.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The website can't be blank"
            )
        }
        return ValidationResult(successful = true)
    }
}