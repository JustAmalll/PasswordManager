package dev.amal.passwordmanager.feature_details.presentation

import dev.amal.passwordmanager.core.domain.models.Password

data class DetailState(
    val password: Password? = null,
    val isLoading: Boolean = false
)