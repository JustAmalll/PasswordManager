package dev.amal.passwordmanager.feature_add_password.domain.repository

import dev.amal.passwordmanager.core.utils.SimpleResource

interface AddPasswordRepository {
    suspend fun addPassword(
        title: String, email: String, password: String, website: String
    ): SimpleResource
}