package dev.amal.passwordmanager.domain.use_cases

import dev.amal.passwordmanager.domain.use_cases.validation.ValidateEmail
import dev.amal.passwordmanager.domain.use_cases.validation.ValidatePassword
import dev.amal.passwordmanager.domain.use_cases.validation.ValidateTitle
import dev.amal.passwordmanager.domain.use_cases.validation.ValidateWebsite

data class UseCases(
    val validateTitle: ValidateTitle,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateWebsite: ValidateWebsite
)