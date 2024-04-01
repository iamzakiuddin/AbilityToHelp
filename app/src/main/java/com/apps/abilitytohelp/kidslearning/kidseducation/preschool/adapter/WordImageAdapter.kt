package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R

class WordImageAdapter(val imagesList: ArrayList<String>) :
    RecyclerView.Adapter<WordImageAdapter.WordImageViewHolder>() {
        var mcontext : Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordImageViewHolder {
        mcontext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_image_item,parent,false)
        return WordImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: WordImageViewHolder, position: Int) {
        if (mcontext!=null){
            Glide.with(mcontext!!)
                .load(imagesList[position])
                .apply( RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.wordImage)
        }
    }


    class WordImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordImage = itemView.findViewById<ImageView>(R.id.wordImage)
    }
}