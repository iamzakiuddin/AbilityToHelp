package com.androidapp.abilitytohelp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.model.ConvoResult

class ConversationAdapter(val basicConvoDataList: ArrayList<ConvoResult>) : RecyclerView.Adapter<ConversationAdapter.ConvoItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvoItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.convo_item,parent,false)
        return ConvoItemHolder(view)
    }
    override fun onBindViewHolder(holder: ConvoItemHolder, position: Int) {
        val model = basicConvoDataList[position]
        holder.usText.text = model.english
        holder.spainText.text = model.spanish
    }

    override fun getItemCount(): Int {
        return basicConvoDataList.size
    }


    class ConvoItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spainText: TextView = itemView.findViewById(R.id.spainText)
        val usText: TextView = itemView.findViewById(R.id.usaText)

    }
}