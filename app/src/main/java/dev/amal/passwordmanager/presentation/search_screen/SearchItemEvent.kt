package dev.amal.passwordmanager.presentation.search_screen

sealed class SearchItemEvent {
    data class OnSearchQueryChange(val query: String) : SearchItemEvent()
}