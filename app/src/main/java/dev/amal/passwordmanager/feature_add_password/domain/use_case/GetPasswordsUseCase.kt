package dev.amal.passwordmanager.feature_add_password.domain.use_case

import androidx.paging.PagingData
import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.feature_add_password.domain.repository.AddPasswordRepository
import dev.amal.passwordmanager.utils.Constants
import kotlinx.coroutines.flow.Flow

class GetPasswordsUseCase(
    private val repository: AddPasswordRepository
) {
    operator fun invoke(): Flow<PagingData<Password>> = repository.passwords
}