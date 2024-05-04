package com.androidapp.abilitytohelp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.model.AbbreviationsResult

class AbbreviationsAdapter(private val dataList: ArrayList<AbbreviationsResult>) : RecyclerView.Adapter<AbbreviationsAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.abbreviations_item,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val model = dataList.get(position)
        holder.termData.text = model.term as? String ?: ""
        holder.definitionData.text = model.definition as? String?: ""
        holder.categoryData.text = model.category as? String?: ""
        holder.categoryNameData.text = model.categoryname as? String?: ""
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val termData: TextView = itemView.findViewById(R.id.termData)
        val definitionData: TextView = itemView.findViewById(R.id.definitionData)
        val categoryData: TextView = itemView.findViewById(R.id.categoryData)
        val categoryNameData: TextView = itemView.findViewById(R.id.categoryNameData)
    }


}