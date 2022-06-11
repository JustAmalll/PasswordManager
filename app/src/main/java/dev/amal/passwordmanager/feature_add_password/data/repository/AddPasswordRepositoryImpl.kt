package dev.amal.passwordmanager.feature_add_password.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import dev.amal.passwordmanager.core.utils.UiText
import dev.amal.passwordmanager.feature_add_password.data.paging.PasswordSource
import dev.amal.passwordmanager.feature_add_password.data.remote.AddPasswordApi
import dev.amal.passwordmanager.feature_add_password.data.remote.request.AddPasswordRequest
import dev.amal.passwordmanager.feature_add_password.domain.repository.AddPasswordRepository
import dev.amal.passwordmanager.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class AddPasswordRepositoryImpl(
    private val api: AddPasswordApi
) : AddPasswordRepository {

    override val passwords: Flow<PagingData<Password>>
        get() = Pager(PagingConfig(
            pageSize = Constants.DEFAULT_PAGE_SIZE)
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
}