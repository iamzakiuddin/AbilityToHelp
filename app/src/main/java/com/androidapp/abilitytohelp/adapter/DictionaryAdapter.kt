package com.androidapp.abilitytohelp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.model.DictonaryResult

class DictionaryAdapter(private val dataList: ArrayList<DictonaryResult>) : RecyclerView.Adapter<DictionaryAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dictionary_item,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val model = dataList.get(position)
        holder.termData.text = model.term
        holder.partsOfSpeechData.text = model.partofspeech
        holder.definitionData.text = model.definition
        holder.exampleData.text = model.example as? String ?: ""
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val termData: TextView = itemView.findViewById(R.id.termData)
        val partsOfSpeechData: TextView = itemView.findViewById(R.id.partofspeechData)
        val definitionData: TextView = itemView.findViewById(R.id.definitionData)
        val exampleData: TextView = itemView.findViewById(R.id.exampleData)
    }


}