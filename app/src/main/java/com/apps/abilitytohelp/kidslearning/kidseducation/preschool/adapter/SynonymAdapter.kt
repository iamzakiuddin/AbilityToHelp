package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R

class SynonymAdapter(val synonymsList: ArrayList<String>) :
    RecyclerView.Adapter<SynonymAdapter.SynonymViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SynonymViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.synonym_item,parent,false)
        return SynonymViewHolder(view)
    }

    override fun getItemCount(): Int {
        return synonymsList.size
    }

    override fun onBindViewHolder(holder: SynonymViewHolder, position: Int) {
        holder.synonym.text = synonymsList[position]
    }


    class SynonymViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val synonym = itemView.findViewById<TextView>(R.id.synonym)
    }
}