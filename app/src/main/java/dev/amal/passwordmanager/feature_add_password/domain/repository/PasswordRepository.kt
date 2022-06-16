package dev.amal.passwordmanager.feature_add_password.domain.repository

import androidx.paging.PagingData
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {

    val passwords: Flow<PagingData<PasswordItem>>

    suspend fun addPassword(
        title: String, email: String, password: String, website: String
    ): SimpleResource

    suspend fun getPasswordDetails(passwordId: String): Resource<PasswordItem>

    suspend fun searchPassword(query: String): Resource<List<PasswordItem>>
}