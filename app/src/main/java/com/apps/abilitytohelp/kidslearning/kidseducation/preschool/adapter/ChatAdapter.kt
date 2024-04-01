package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R

class ChatAdapter(val dataList: ArrayList<String>) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_layout,parent,false)
        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.messageTextView.text = dataList.get(position) ?: ""
    }


    class MessageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val messageTextView: TextView = itemView.findViewById(R.id.messageText)
    }
}