package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R

class EnglishAdapter(private val dataList: HashMap<String,String>) : RecyclerView.Adapter<EnglishAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.english_word_item,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        println("HEY "+dataList.size)
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val key = dataList.keys.elementAt(position)
        val value = dataList[key]
        holder.word.text = key
        holder.wordSentence.text = value
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val word: TextView = itemView.findViewById(R.id.word)
        val wordSentence: TextView = itemView.findViewById(R.id.wordSentence)
    }


}