package dev.amal.passwordmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.amal.passwordmanager.data.models.Password

@Database(entities = [Password::class], version = 1, exportSchema = false)
abstract class PasswordDatabase: RoomDatabase() {
    abstract fun passwordDao(): PasswordManagerDao
}