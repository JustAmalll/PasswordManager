package dev.amal.passwordmanager.feature_add_password.domain.use_case

import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository

class GetPasswordDetailsUseCase(
    private val repository: PasswordRepository
) {
    suspend operator fun invoke(postId: String): Resource<Password> =
        repository.getPasswordDetails(postId)
}