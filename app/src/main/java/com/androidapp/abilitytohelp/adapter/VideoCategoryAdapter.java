package com.androidapp.abilitytohelp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.activity.video.ListVideoActivity;
import com.androidapp.abilitytohelp.customclasses.Constant;

/**
 * Created by Naynesh Patel on 11-Feb-19.
 */
public class VideoCategoryAdapter extends RecyclerView.Adapter<VideoCategoryAdapter.ViewHolder> {

    Context context;
    String[] videocategory;
    int[] tumbnailList;
    Activity activity;

    public VideoCategoryAdapter(Context context, String[] videocategory, int[] tumbnailList, Activity activity) {
        this.context = context;
        this.videocategory = videocategory;
        this.tumbnailList = tumbnailList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_card_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(tumbnailList[i]).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgHomeCategory);
        viewHolder.lloutHome.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bubble_anim));
        viewHolder.lloutHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Constant.VIDEO_CATEGORY_ID=String.valueOf(i);
                context.startActivity(new Intent(context, ListVideoActivity.class).putExtra("Category",videocategory[i]));
                activity.overridePendingTransition(R.anim.enter_anim,R.anim.exit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videocategory.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeCategory;
        LinearLayout lloutHome;


        public ViewHolder(@NonNull View view) {
            super(view);
            imgHomeCategory = view.findViewById(R.id.imgHomeCategory);
            lloutHome = view.findViewById(R.id.lloutCardHome);
        }
    }
}
