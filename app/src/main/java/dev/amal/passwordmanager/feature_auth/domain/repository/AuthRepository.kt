package dev.amal.passwordmanager.feature_auth.domain.repository

import dev.amal.passwordmanager.feature_auth.domain.models.AuthResult

interface AuthRepository {
    suspend fun signUp(username: String, password: String): AuthResult<Unit>
    suspend fun signIn(username: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}