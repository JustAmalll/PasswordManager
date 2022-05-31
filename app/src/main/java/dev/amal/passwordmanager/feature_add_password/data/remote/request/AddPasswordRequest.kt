package dev.amal.passwordmanager.feature_add_password.data.remote.request

data class AddPasswordRequest(
    val title: String,
    val email: String,
    val password: String,
    val website: String
)