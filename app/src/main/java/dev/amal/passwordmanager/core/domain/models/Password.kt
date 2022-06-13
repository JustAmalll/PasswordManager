package dev.amal.passwordmanager.core.domain.models

data class Password(
    val id: String,
    val title: String,
    val email: String,
    val password: String,
    val website: String
)