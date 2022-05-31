package dev.amal.passwordmanager.feature_add_password.data.remote

import dev.amal.passwordmanager.core.data.response.BasicApiResponse
import dev.amal.passwordmanager.feature_add_password.data.remote.request.AddPasswordRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AddPasswordApi {

    @POST("/api/add/password")
    suspend fun addPassword(
        @Body title: AddPasswordRequest
    ): BasicApiResponse<Unit>

}