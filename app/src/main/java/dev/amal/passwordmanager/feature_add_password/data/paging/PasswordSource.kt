package dev.amal.passwordmanager.feature_add_password.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.feature_add_password.data.remote.PasswordApi
import dev.amal.passwordmanager.utils.Constants
import retrofit2.HttpException
import java.io.IOException

class PasswordSource(
    private val api: PasswordApi
) : PagingSource<Int, PasswordItem>() {

    private var currentPage = 0

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PasswordItem> = try {
        val nextPage = params.key ?: currentPage
        val activities = api.getPasswords(
            page = nextPage, pageSize = Constants.DEFAULT_PAGE_SIZE
        )
        LoadResult.Page(
            data = activities,
            prevKey = if (nextPage == 0) null else nextPage - 1,
            nextKey = if (activities.isEmpty()) null else currentPage + 1
        ).also { currentPage++ }
    } catch (exception: IOException) {
        LoadResult.Error(exception)
    } catch (exception: HttpException) {
        LoadResult.Error(exception)
    }

    override fun getRefreshKey(
        state: PagingState<Int, PasswordItem>
    ): Int? = state.anchorPosition
}