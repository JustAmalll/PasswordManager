package dev.amal.passwordmanager.feature_add_password.presentation

sealed class AddPasswordFormEvent {

    data class TitleChanged(val title: String) : AddPasswordFormEvent()
    data class EmailChanged(val email: String) : AddPasswordFormEvent()
    data class ItemChanged(val password: String) : AddPasswordFormEvent()
    data class WebsiteChanged(val website: String) : AddPasswordFormEvent()

    object Submit : AddPasswordFormEvent()
}