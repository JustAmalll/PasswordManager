package dev.amal.passwordmanager.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.amal.passwordmanager.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Password(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val email: String,
    val password: String,
    val website: String
)