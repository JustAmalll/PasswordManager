package dev.amal.passwordmanager.feature_add_card.data.repository

import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import dev.amal.passwordmanager.core.utils.UiText
import dev.amal.passwordmanager.feature_add_card.data.remote.CardApi
import dev.amal.passwordmanager.feature_add_card.data.remote.request.CardRequest
import dev.amal.passwordmanager.feature_add_card.domain.repository.CardRepository
import retrofit2.HttpException
import java.io.IOException

class CardRepositoryImpl(
    private val api: CardApi
) : CardRepository {

    override suspend fun addCard(
        title: String,
        cardHolderName: String,
        cardNumber: String,
        expirationDate: String,
        CVV: String,
        cardPin: String,
        ZIP: String
    ): SimpleResource = try {
        val request = CardRequest(
            title = title,
            cardHolderName = cardHolderName,
            cardNumber = cardNumber,
            expirationDate = expirationDate,
            CVV = CVV,
            cardPin = cardPin,
            ZIP = ZIP
        )
        val response = api.addCard(request)
        if (response.successful) {
            Resource.Success(Unit)
        } else {
            response.message?.let { msg ->
                Resource.Error(UiText.DynamicString(msg))
            } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
        }
    } catch (e: IOException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
        )
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

}