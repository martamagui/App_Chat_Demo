package com.example.app_demo_chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_demo_chat.R
import com.example.app_demo_chat.databinding.FragmentChatBinding
import com.example.app_demo_chat.model.Message


class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = ChatAdapter()

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
//        binding.rvChat.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        binding.rvChat.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        val msgList: MutableList<Message> = mutableListOf()
        msgList.add(Message(1,2,"hi", "2/7/2021"))
        msgList.add(Message(2,1,"hiaa", "2/7/2021"))
        msgList.add(Message(3,2,"hid", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        msgList.add(Message(4,1,"hir", "2/7/2021"))
        adapter.submitList(msgList)
        binding.btnSend.setOnClickListener {
            val msgText = binding.etMsg.text
            msgList.add(Message(0,5,msgText.toString(),"1/10/2022"))
            adapter.submitList(msgList)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}