package dev.amal.passwordmanager.feature_add_password.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import dev.amal.passwordmanager.core.utils.UiText
import dev.amal.passwordmanager.feature_add_password.data.paging.PasswordSource
import dev.amal.passwordmanager.feature_add_password.data.remote.PasswordApi
import dev.amal.passwordmanager.feature_add_password.data.remote.request.AddPasswordRequest
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository
import dev.amal.passwordmanager.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class PasswordRepositoryImpl(
    private val api: PasswordApi
) : PasswordRepository {

    override val passwords: Flow<PagingData<PasswordItem>>
        get() = Pager(
            PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)
        ) { PasswordSource(api) }.flow

    override suspend fun addPassword(
        title: String, email: String, password: String, website: String
    ): SimpleResource = try {
        val request = AddPasswordRequest(
            title = title,
            email = email,
            password = password,
            website = website
        )
        val response = api.addPassword(request)
        if (response.successful) {
            Resource.Success(Unit)
        } else {
            response.message?.let { msg ->
                Resource.Error(UiText.DynamicString(msg))
            } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
        }
    } catch (e: IOException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
        )
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun getPasswordDetails(
        passwordId: String
    ): Resource<PasswordItem> = try {
        val response = api.getPasswordDetails(passwordId = passwordId)
        if (response.successful) Resource.Success(response.data)
        else {
            response.message?.let { msg ->
                Resource.Error(UiText.DynamicString(msg))
            } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
        }
    } catch (e: IOException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
        )
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun searchPassword(
        query: String
    ): Resource<List<PasswordItem>> = try {
        val response = api.searchPassword(query)
        Resource.Success(
            data = response.map { it.toPasswordItem() }
        )
    } catch (e: IOException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
        )
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

}