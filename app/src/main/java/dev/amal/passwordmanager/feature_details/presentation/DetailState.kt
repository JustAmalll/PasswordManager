package dev.amal.passwordmanager.feature_details.presentation

import dev.amal.passwordmanager.core.domain.models.PasswordItem

data class DetailState(
    val passwordItem: PasswordItem? = null,
    val isLoading: Boolean = false
)