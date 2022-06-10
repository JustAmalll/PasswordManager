package dev.amal.passwordmanager.feature_add_password.domain.repository

import androidx.paging.PagingData
import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import kotlinx.coroutines.flow.Flow

interface AddPasswordRepository {

    val passwords: Flow<PagingData<Password>>

    suspend fun addPassword(
        title: String, email: String, password: String, website: String
    ): SimpleResource
}