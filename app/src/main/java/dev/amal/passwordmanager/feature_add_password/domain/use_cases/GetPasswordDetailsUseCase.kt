package dev.amal.passwordmanager.feature_add_password.domain.use_cases

import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository

class GetPasswordDetailsUseCase(
    private val repository: PasswordRepository
) {
    suspend operator fun invoke(postId: String): Resource<PasswordItem> =
        repository.getPasswordDetails(postId)
}