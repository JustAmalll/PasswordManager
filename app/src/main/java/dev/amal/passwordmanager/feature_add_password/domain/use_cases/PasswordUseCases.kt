package dev.amal.passwordmanager.feature_add_password.domain.use_cases

data class PasswordUseCases(
    val getPasswordsUseCase: GetPasswordsUseCase,
    val addPasswordUseCase: AddPasswordUseCase,
    val getPasswordDetailsUseCase: GetPasswordDetailsUseCase,
    val searchPasswordUseCase: SearchPasswordUseCase
)