package com.androidapp.abilitytohelp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.interfaces.ConvoItemClick
import com.androidapp.abilitytohelp.model.ConvoResult

class ConversationAdapter(val basicConvoDataList: ArrayList<ConvoResult>, val convoItemClick: ConvoItemClick) : RecyclerView.Adapter<ConversationAdapter.ConvoItemHolder>() {

    var currentPlayingPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvoItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.convo_item,parent,false)
        return ConvoItemHolder(view)
    }
    override fun onBindViewHolder(holder: ConvoItemHolder, position: Int) {
        val model = basicConvoDataList[position]
        holder.usText.text = model.english
        holder.spainText.text = model.spanish
        holder.itemView.setOnClickListener {

            convoItemClick.onItemClick(position,currentPlayingPosition)
        }
        if (model.isSelected){
            holder.overlay.visibility = View.VISIBLE
        } else {
            holder.overlay.visibility = View.GONE
        }
        //holder.overlay.visibility = if (position==currentPlayingPosition) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return basicConvoDataList.size
    }


    class ConvoItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spainText: TextView = itemView.findViewById(R.id.spainText)
        val usText: TextView = itemView.findViewById(R.id.usaText)
        val overlay: View = itemView.findViewById(R.id.overlay)

    }

    fun updatePlayingPosition(position: Int) {
        if (position!=-1){
            val previousPlayingPosition = currentPlayingPosition
            currentPlayingPosition = position
            notifyItemChanged(previousPlayingPosition)
            notifyItemChanged(currentPlayingPosition)
        }
    }
}