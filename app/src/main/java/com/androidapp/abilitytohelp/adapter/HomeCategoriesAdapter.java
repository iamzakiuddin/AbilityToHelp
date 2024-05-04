package com.androidapp.abilitytohelp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.activity.exam.ListenGuessActivity;
import com.androidapp.abilitytohelp.activity.exam.LookChooseActivity;
import com.androidapp.abilitytohelp.activity.SubActivity;

/**
 * Created by Naynesh Patel on 06-Feb-19.
 */
public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomeCategory;
        LinearLayout lloutHome;
        TextView categoryTitle;
        CardView cardView;

        ViewHolder(@NonNull View view) {
            super(view);
            this.imgHomeCategory = view.findViewById(R.id.imgHomeCategory);
            this.lloutHome = view.findViewById(R.id.lloutCardHome);
            this.categoryTitle = view.findViewById(R.id.title);
            this.cardView = view.findViewById(R.id.cardView);
        }
    }

    Context context;
    int[] mainCategoryList;
    String[] homeCategoryTitles;
    int TYPE;
    Activity activity;

    public HomeCategoriesAdapter(Context context, int[] mainCategoryList, String[] homeCategoryTitles, int type, Activity activity) {
        this.context = context;
        this.mainCategoryList = mainCategoryList;
        this.homeCategoryTitles = homeCategoryTitles;
        this.TYPE = type;
        this.activity = activity;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_list_home, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(mainCategoryList[i]).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgHomeCategory);
        viewHolder.categoryTitle.setText(homeCategoryTitles[i]);
        viewHolder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bubble_anim));
        viewHolder.lloutHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TYPE == 1) {
                    Intent intent = new Intent(context, SubActivity.class);
                    intent.putExtra("categoryPosition", i);
                    intent.putExtra("Category", homeCategoryTitles[i]);
                    intent.putExtra("Type",TYPE);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                } else if (TYPE == 2) {
                    Intent intent = new Intent(context, LookChooseActivity.class);
                    intent.putExtra("categoryPosition", i);
                    intent.putExtra("SubCate", homeCategoryTitles[i]);
                    intent.putExtra("TYPE", 2);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                } else if (TYPE == 3) {
                    Intent intent = new Intent(context, ListenGuessActivity.class);
                    intent.putExtra("categoryPosition", i);
                    intent.putExtra("SubCate", homeCategoryTitles[i]);
                    intent.putExtra("TYPE", 3);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                }


            }
        });
    }

    public int getItemCount() {
        return mainCategoryList.length;
    }
}