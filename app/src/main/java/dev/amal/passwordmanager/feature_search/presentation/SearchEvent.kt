package dev.amal.passwordmanager.feature_search.presentation

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String) : SearchEvent()
}