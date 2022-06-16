package dev.amal.passwordmanager.feature_search.presentation

import dev.amal.passwordmanager.core.domain.models.PasswordItem

data class SearchState(
    val passwordItems: List<PasswordItem> = emptyList(),
    val isLoading: Boolean = false
)
