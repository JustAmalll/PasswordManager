package dev.amal.passwordmanager.core.data.response

import dev.amal.passwordmanager.core.domain.models.PasswordItem

data class PasswordDto(
    val id: String,
    val title: String,
    val email: String,
    val password: String,
    val website: String
) {
    fun toPasswordItem(): PasswordItem =
        PasswordItem(id, title, email, password, website)
}