package dev.amal.passwordmanager.feature_search.presentation

sealed class SearchItemEvent {
    data class OnSearchQueryChange(val query: String) : SearchItemEvent()
}