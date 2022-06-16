package dev.amal.passwordmanager.feature_add_password.domain.use_case

import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository

class SearchPasswordUseCase(
    private val repository: PasswordRepository
) {
    suspend operator fun invoke(query: String): Resource<List<PasswordItem>> {
        if (query.isBlank()) return Resource.Success(data = emptyList())
        return repository.searchPassword(query)
    }
}