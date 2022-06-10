package dev.amal.passwordmanager.core.utils

interface Paginator<T> {
    suspend fun loadNextItems()
}