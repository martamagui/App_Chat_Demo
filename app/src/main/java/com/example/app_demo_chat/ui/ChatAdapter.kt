package com.example.app_demo_chat.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_demo_chat.databinding.MessageItemBinding
import com.example.app_demo_chat.model.Message

class ChatAdapter : ListAdapter<Message, ChatAdapter.ViewHolder>(MessageItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MessageItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMsg = getItem(position)
        holder.binding.tvChatText.text = itemMsg.msg
        holder.binding.tvChatDate.text = itemMsg.date
        if(itemMsg.userIdFk==0){
            holder.binding.cvMsg.setCardBackgroundColor(Color.rgb(19,20,92))
            holder.binding.tvChatText.setTextColor(Color.rgb(238,238,246))
            holder.binding.tvChatDate.setTextColor(Color.rgb(238,238,246))
        }else{
            holder.binding.cvMsg.setCardBackgroundColor(Color.rgb(238,238,246))
            holder.binding.tvChatText.setTextColor(Color.rgb(19,20,92))
            holder.binding.tvChatDate.setTextColor(Color.rgb(19,20,92))
        }
    }
    inner class ViewHolder(val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}

class MessageItemCallback : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean =
        oldItem.msgId == newItem.msgId

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean =
        oldItem.msgId == newItem.msgId
}