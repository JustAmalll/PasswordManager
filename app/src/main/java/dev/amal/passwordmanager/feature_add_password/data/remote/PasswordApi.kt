package dev.amal.passwordmanager.feature_add_password.data.remote

import dev.amal.passwordmanager.core.data.response.BasicApiResponse
import dev.amal.passwordmanager.core.data.response.PasswordDto
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.feature_add_password.data.remote.request.AddPasswordRequest
import dev.amal.passwordmanager.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PasswordApi {

    @GET("/user/passwords")
    suspend fun getPasswords(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<PasswordItem>

    @POST("/add/password")
    suspend fun addPassword(
        @Body title: AddPasswordRequest
    ): BasicApiResponse<Unit>

    @GET("/password/details")
    suspend fun getPasswordDetails(
        @Query("passwordId") passwordId: String
    ): BasicApiResponse<PasswordItem>

    @GET("/password/search")
    suspend fun searchPassword(
        @Query("query") query: String
    ): List<PasswordDto>

}