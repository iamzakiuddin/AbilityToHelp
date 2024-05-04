package com.androidapp.abilitytohelp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.model.PhrasesResult

class PhrasesAdapter(private val dataList: ArrayList<PhrasesResult>) : RecyclerView.Adapter<PhrasesAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phrases_item,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val model = dataList.get(position)
        holder.termData.text = model.term
        holder.explanationData.text = model.explanation
        holder.exampleData.text = model.example
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val termData: TextView = itemView.findViewById(R.id.termData)
        val explanationData: TextView = itemView.findViewById(R.id.explanationData)
        val exampleData: TextView = itemView.findViewById(R.id.exampleData)
    }


}