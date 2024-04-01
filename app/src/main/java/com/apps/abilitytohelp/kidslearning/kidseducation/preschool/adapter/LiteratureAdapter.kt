package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.LiteratureResult

class LiteratureAdapter(private val dataList: ArrayList<LiteratureResult>) : RecyclerView.Adapter<LiteratureAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.literature_item_layout,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val model = dataList.get(position)
        holder.titleData.text = model.title
        holder.subtitleData.text = model.subtitle
        holder.writerData.text = model.writer
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleData: TextView = itemView.findViewById(R.id.titleData)
        val subtitleData: TextView = itemView.findViewById(R.id.subtitleData)
        val writerData: TextView = itemView.findViewById(R.id.writerData)
    }


}