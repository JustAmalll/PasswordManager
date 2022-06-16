package dev.amal.passwordmanager.feature_add_password.domain.use_case

import androidx.paging.PagingData
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow

class GetPasswordsUseCase(
    private val repository: PasswordRepository
) {
    operator fun invoke(): Flow<PagingData<PasswordItem>> = repository.passwords
}