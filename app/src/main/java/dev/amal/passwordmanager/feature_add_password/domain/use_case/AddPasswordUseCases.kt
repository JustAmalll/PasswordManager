package dev.amal.passwordmanager.feature_add_password.domain.use_case

data class AddPasswordUseCases(
    val getPasswordsUseCase: GetPasswordsUseCase,
    val addPasswordUseCase: AddPasswordUseCase
)