package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R;

/**
 * Created by Naynesh Patel on 06-Feb-19.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    int[] arrOfCategory;
    int[] categoriesTitles;
    onClickMain onClickMain;

    public HomeAdapter(Context context, int[] arrOfCategory,int[] categoriesTitles, onClickMain onClickMain) {
        this.context = context;
        this.arrOfCategory = arrOfCategory;
        this.onClickMain = onClickMain;
        this.categoriesTitles = categoriesTitles;
    }

    public int getItemCount() {
        return arrOfCategory.length;
    }


    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_list_start, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context).load(arrOfCategory[i]).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgThumbnail);
        viewHolder.title.setText(categoriesTitles[i]);
        viewHolder.cVHomeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMain.onClickCategory(i);

            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cVHomeCategories;
        ImageView imgThumbnail;
        TextView title;
        ViewHolder(@NonNull View view) {
            super(view);
            this.cVHomeCategories = view.findViewById(R.id.cVHomeCategories);
            this.imgThumbnail = view.findViewById(R.id.imgThumbnail);
            this.title = view.findViewById(R.id.title);
        }
    }

    public interface onClickMain{
        void onClickCategory(int pos);
    }

}



