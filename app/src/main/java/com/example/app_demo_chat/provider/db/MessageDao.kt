package com.example.app_demo_chat.provider.db

import androidx.room.*

@Dao
interface MessageDao {
    //Aquí van las queries que queremos que nos devuelvan las bases de datos
    @Query("SELECT * FROM message")
    fun findAll(): List<MessageEntity>
//    @Query("SELECT * FROM message ORDER BY msg_id")
//    fun findAllSorted(): List<Message>
    @Insert
    fun createMessage(messageEntity: MessageEntity)
    @Insert
    fun createMessages(messageEntity: List<MessageEntity>)
    @Update
    fun updateMessage(messageEntity: MessageEntity)
    @Delete
    fun deleteMessage(messageEntity: MessageEntity)
    @Delete
    fun deleteAllMessages(messageEntity: List<MessageEntity>)
}
