package dev.amal.passwordmanager.data.repositories

import dagger.hilt.android.scopes.ViewModelScoped
import dev.amal.passwordmanager.data.PasswordManagerDao
import dev.amal.passwordmanager.data.models.Password
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PasswordRepository @Inject constructor(
    private val passwordDao: PasswordManagerDao
) {

    val getAllItems: Flow<List<Password>> = passwordDao.getAllItems()

    fun getSelectedItem(itemId: Int): Flow<Password> =
        passwordDao.getSelectedItem(itemId = itemId)

    suspend fun addItem(password: Password) {
        passwordDao.addPassword(password = password)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Password>> =
        passwordDao.searchDatabase(searchQuery = searchQuery)

}