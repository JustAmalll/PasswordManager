package dev.amal.passwordmanager.feature_add_card.data.remote

import dev.amal.passwordmanager.core.data.response.BasicApiResponse
import dev.amal.passwordmanager.feature_add_card.data.remote.request.CardRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface CardApi {

    @POST("/add/card")
    suspend fun addCard(
        @Body title: CardRequest
    ): BasicApiResponse<Unit>

}