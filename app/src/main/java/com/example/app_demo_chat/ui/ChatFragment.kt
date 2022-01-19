package com.example.app_demo_chat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_demo_chat.MainActivity
import com.example.app_demo_chat.databinding.FragmentChatBinding
import com.example.app_demo_chat.model.Message
import com.example.app_demo_chat.provider.api.ChatApi
import com.example.app_demo_chat.provider.db.MessageEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = ChatAdapter()
    private val msgList: MutableList<Message> = mutableListOf()
    private val userId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvChat.adapter = adapter
        binding.rvChat.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        getMessage()
        binding.btnSend.setOnClickListener {
            val msgText: String = binding.etMsg.text.toString()
            sendMessage(msgText)
            adapter.submitList(msgList)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun getMessage() {
        ChatApi.service.getMessage().enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    msgList.clear()
                    response.body()?.let { msgList.addAll(it) }
                    msgList.sortByDescending { it.msgId }
                    adapter.submitList(msgList)
                    adapter.notifyDataSetChanged()
                    msgApiListToDB()
                } else {
                    getMsgListFromDB()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }
            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                getMsgListFromDB()
                Log.e("requestData", "error", t)
            }
        })
    }

    private fun sendMessage(msg: String) {
        ChatApi.service.addMessage(Message(msgList.size, userId, msg, Date().toString()))
            .enqueue(object : Callback<List<Message>> {
                override fun onResponse(
                    call: Call<List<Message>>,
                    response: Response<List<Message>>
                ) {
                    if (response.isSuccessful) {
                        msgList.clear()
                        response.body()?.let { msgList.addAll(it) }
                        msgList.sortByDescending { it.msgId }
                        adapter.submitList(msgList)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(context, "Error al enviar", Toast.LENGTH_SHORT).show()
                        val code = response.code()
                        val message = response.message()
                        Log.e("requestData", "error en la respuesta: $code <> $message")
                    }
                }

                override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                    Log.e("requestData", "error", t)
                }
            })
    }
    private fun msgApiListToDB() {
        val localMessageEntity: MutableList<MessageEntity> = mutableListOf()
        for (item in msgList) {
            localMessageEntity.add(MessageEntity(item.msgId, item.msg, item.date, item.userIdFk))
        }
        (activity as MainActivity).db.messageDao()
            .deleteAllMessages((activity as MainActivity).db.messageDao().findAll())
        (activity as MainActivity).db.messageDao().createMessages(localMessageEntity)
    }

    private fun getMsgListFromDB() {
        val localMsgEntityList = (activity as MainActivity).db.messageDao().findAll()
        val localMsgs = mutableListOf<Message>()
        for (item in localMsgEntityList) {
            localMsgs.add(Message(item.msgId, item.userIdFk, item.text, item.date.toString()))
        }
        adapter.submitList(localMsgs)
    }



}