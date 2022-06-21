package dev.amal.passwordmanager.feature_add_card.domain.repository

import dev.amal.passwordmanager.core.utils.SimpleResource

interface CardRepository {
    suspend fun addCard(
        title: String,
        cardHolderName: String,
        cardNumber: String,
        expirationDate: String,
        CVV: String,
        cardPin: String,
        ZIP: String
    ): SimpleResource
}