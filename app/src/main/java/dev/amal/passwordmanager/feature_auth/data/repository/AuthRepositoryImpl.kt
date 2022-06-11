package dev.amal.passwordmanager.feature_auth.data.repository

import android.content.SharedPreferences
import dev.amal.passwordmanager.feature_auth.domain.models.AuthResult
import dev.amal.passwordmanager.feature_auth.data.remote.AuthApi
import dev.amal.passwordmanager.feature_auth.data.remote.request.AuthRequest
import dev.amal.passwordmanager.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {

    override suspend fun signUp(
        username: String, password: String
    ): AuthResult<Unit> = try {
        api.signUp(request = AuthRequest(username = username, password = password))
        signIn(username, password)
    } catch (e: HttpException) {
        if (e.code() == 401) AuthResult.Unauthorized() else AuthResult.UnknownError()
    } catch (e: Exception) {
        AuthResult.UnknownError()
    }

    override suspend fun signIn(
        username: String, password: String
    ): AuthResult<Unit> = try {
        val response = api.signIn(
            request = AuthRequest(username = username, password = password)
        )
        prefs.edit().putString("jwt", response.token).apply()
        AuthResult.Authorized()
    } catch (e: HttpException) {
        if (e.code() == 401) AuthResult.Unauthorized() else AuthResult.UnknownError()
    } catch (e: Exception) {
        AuthResult.UnknownError()
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) AuthResult.Unauthorized() else AuthResult.UnknownError()
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}