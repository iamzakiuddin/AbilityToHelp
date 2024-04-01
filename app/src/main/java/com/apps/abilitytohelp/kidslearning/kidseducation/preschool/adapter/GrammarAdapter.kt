package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.IdentifiedMistake

class GrammarAdapter(private val dataList: ArrayList<IdentifiedMistake>) : RecyclerView.Adapter<GrammarAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grammar_item_layout,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val model = dataList.get(position)
        holder.categoryType.text = model.category
        holder.contextData.text = model.context
        holder.messageData.text = model.message
        val replacements = (0 until model.replacements.size).joinToString(", ") {
            model.replacements[it]
        }
        holder.replacementData.text = replacements
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryType: TextView = itemView.findViewById(R.id.categoryType)
        val contextData: TextView = itemView.findViewById(R.id.contextData)
        val messageData: TextView = itemView.findViewById(R.id.messageData)
        val replacementData: TextView = itemView.findViewById(R.id.replacementData)
    }


}