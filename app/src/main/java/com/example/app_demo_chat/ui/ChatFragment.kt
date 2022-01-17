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
import com.example.app_demo_chat.databinding.FragmentChatBinding
import com.example.app_demo_chat.model.Message
import com.example.app_demo_chat.model.MessageBody
import com.example.app_demo_chat.provider.ChatApi
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

    private fun getMessage() {
        ChatApi.service.getMessage().enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    msgList.clear()
                    response.body()?.let { msgList.addAll(it) }
                    adapter.submitList(msgList)
                    adapter.notifyDataSetChanged()
                } else {
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

    private fun sendMessage(msg: String) {
        ChatApi.service.addMessage(Message(msgList.size, 0, msg, Date().toString())).enqueue(object : Callback<List<Message>> {
                override fun onResponse(call:  Call<List<Message>>, response: Response<List<Message>>) {
                    if (response.isSuccessful) {
                        msgList.clear()
                        response.body()?.let { msgList.addAll(it) }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}