package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.PeriodicElementResponse

class PeriodicTableAdapter(private val dataList: PeriodicElementResponse, private val onClickItem: onClickPeriodicElement) : RecyclerView.Adapter<PeriodicTableAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.periodic_element,parent,false)
        return ElementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        holder.elementSymbol.text = dataList[position].symbol
        holder.elementName.text = dataList[position].name
        holder.itemView.setOnClickListener {
            onClickItem.onClickElement(position)
        }
    }


    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val elementSymbol: TextView = itemView.findViewById(R.id.elementSymbol)
        val elementName: TextView = itemView.findViewById(R.id.elementName)
    }


    interface onClickPeriodicElement {
        fun onClickElement(pos: Int)
    }

}