package com.androidapp.abilitytohelp.activity.video;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.adapter.VideoListAdapter;
import com.androidapp.abilitytohelp.database.DatabaseHelper;
import com.androidapp.abilitytohelp.model.ModelVideo;
import com.androidapp.abilitytohelp.utils.Utils;

import java.util.ArrayList;

public class ListVideoActivity extends AppCompatActivity {

    Context context;
    DatabaseHelper databaseHelper;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    LinearLayout backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        getSupportActionBar().hide();
        context=this;
        initDefine();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }



    private void initDefine() {
        rvVideoList=findViewById(R.id.rvVideoList);
        backBtn = findViewById(R.id.backBtn);
        TextView txtTitleSubHome=findViewById(R.id.txtTitleSubHome);
        Intent intent=getIntent();
        txtTitleSubHome.setText(intent.getStringExtra("Category"));
        try {
            setRvVideoListAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
    }

    RecyclerView rvVideoList;
    ArrayList<ModelVideo> arrOfVideoList;
    VideoListAdapter videoListAdapter;
    private void setRvVideoListAdapter() {
        try {
            databaseHelper=new DatabaseHelper(context);
            arrOfVideoList=databaseHelper.getVideoDetails();
            StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            rvVideoList.setLayoutManager(staggeredGridLayoutManager);
            videoListAdapter=new VideoListAdapter(context,arrOfVideoList);
            rvVideoList.setAdapter(videoListAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickBack(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
    }
}
