package com.androidapp.abilitytohelp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.androidapp.abilitytohelp.model.parseapismodels.Result;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.activity.FullScreenActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naynesh Patel on 06-Feb-19.
 */
public class SubHomeAdapter extends RecyclerView.Adapter<SubHomeAdapter.ViewHolder> {

    Context context;
    List<Result> learningDataModelArrayList;
    int p, TYPE;
    Activity mactivity;
    String apiEndPoint;

    public SubHomeAdapter(Context context, String apiEndPoint, List<Result> learningDataModelArrayList, int i, int position, Activity activity) {
        this.context = context;
        this.learningDataModelArrayList = learningDataModelArrayList;
        this.p = i;
        this.TYPE = position;
        this.mactivity = activity;
        this.apiEndPoint = apiEndPoint;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgSubHome;

        ViewHolder(@NonNull View view) {
            super(view);
            this.cardView = view.findViewById(R.id.cvCardSubHome);
            this.imgSubHome = view.findViewById(R.id.imgSubHome);
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_sub_list_home, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context).load(learningDataModelArrayList.get(i).getImage().getUrl()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgSubHome);

        viewHolder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bubble_anim));

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Gson gson = new Gson();
                Intent intent = new Intent(context, FullScreenActivity.class);
                intent.putExtra("categoryPosition", p);
                intent.putExtra("selectedPosition", i);
                intent.putParcelableArrayListExtra("dataList",(ArrayList<Result>) learningDataModelArrayList);
                context.startActivity(intent);
                mactivity.overridePendingTransition(R.anim.enter_anim,R.anim.exit);
            }
        });
    }

    public int getItemCount() {
        return learningDataModelArrayList.size();
    }
}

