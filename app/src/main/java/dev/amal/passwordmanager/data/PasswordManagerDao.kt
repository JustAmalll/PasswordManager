package dev.amal.passwordmanager.data

import androidx.room.*
import dev.amal.passwordmanager.core.domain.models.Password
import kotlinx.coroutines.flow.Flow

//@Dao
//interface PasswordManagerDao {
//
//    @Query("SELECT * FROM password_manager_table ORDER BY id ASC")
//    fun getAllItems(): Flow<List<Password>>
//
//    @Query("SELECT * FROM password_manager_table WHERE id=:itemId")
//    fun getSelectedItem(itemId: Int): Flow<Password>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addPassword(password: Password)
//
//    @Query("SELECT * FROM password_manager_table WHERE title LIKE :searchQuery")
//    fun searchDatabase(searchQuery: String): Flow<List<Password>>
//
//    @Update
//    suspend fun updatePassword(password: Password)
//
//    @Delete
//    suspend fun deletePassword(password: Password)
//
//}