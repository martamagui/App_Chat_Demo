package com.example.app_demo_chat.db

import androidx.room.*
import com.example.app_demo_chat.model.Message

@Dao
interface MessageDao {
    //Aquí van las queries que queremos que nos devuelvan las bases de datos
    @Query("SELECT * FROM message")
    fun findAll(): List<MessageEntity>
//    @Query("SELECT * FROM message ORDER BY msg_id")
//    fun findAllSorted(): List<Message>
    @Insert
    fun createMessage(messageEntity: MessageEntity)
    @Update
    fun updateMessage(messageEntity: MessageEntity)
    @Delete
    fun deleteMessage(messageEntity: MessageEntity)
}
