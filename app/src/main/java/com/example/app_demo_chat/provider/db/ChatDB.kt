package com.example.app_demo_chat.provider.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageEntity::class], version = 1)
abstract class ChatDB: RoomDatabase() {
    abstract fun messageDao(): MessageDao
}