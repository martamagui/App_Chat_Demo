package com.example.app_demo_chat.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "message")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "msg_id")val msgId: Int = 0,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date", defaultValue = "12-01-2022")val date: String? = Date().toString(),
    @ColumnInfo(name = "user_id",) val userIdFk: Int
)
