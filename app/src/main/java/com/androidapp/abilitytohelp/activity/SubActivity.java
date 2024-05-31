package com.androidapp.abilitytohelp.activity;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.adapter.SubHomeAdapter;
import com.androidapp.abilitytohelp.model.LearningDataModel;
import com.androidapp.abilitytohelp.model.parseapismodels.ParseApiResponse;
import com.androidapp.abilitytohelp.model.parseapismodels.Result;
import com.androidapp.abilitytohelp.network.NetworkResources;
import com.androidapp.abilitytohelp.utils.Utils;
import com.androidapp.abilitytohelp.viewmodels.ParseApisViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubActivity extends AppCompatActivity {

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    LinearLayout backBtn;
    private String apiEndPoint;
    ParseApisViewModel parseApisViewModel;
    ProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        getSupportActionBar().hide();
        context = this;
        parseApisViewModel = new ViewModelProvider(this).get(ParseApisViewModel.class);
        initDefine();
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }



    TextView txtTitleSubHome;
    int position;
    int TYPE;
    private void initDefine() {
        loadingView = findViewById(R.id.loading);
        rvSubHome = findViewById(R.id.rvSubHome);
        txtTitleSubHome = findViewById(R.id.txtTitleSubHome);
        Intent intent = getIntent();
        position = intent.getIntExtra("categoryPosition", 0);
        String category = intent.getStringExtra("Category");
        TYPE=intent.getIntExtra("Type",0);
        apiEndPoint = intent.getStringExtra("desc_data");
        txtTitleSubHome.setText(category);
        backBtn = findViewById(R.id.backBtn);
        parseApisViewModel.hitParseApi(apiEndPoint);
        prepareDataForLearning();
    }


    List<Result> learningDataModelArrayList = new ArrayList<>();

    public void prepareDataForLearning() {
        setRvAdapter();
        parseApisViewModel.getParseResponseObserver().observe(this, parseApiResponseNetworkResources -> {
            switch (Objects.requireNonNull(parseApiResponseNetworkResources.getStatus())) {
                case LOADING -> loadingView.setVisibility(View.VISIBLE);

                case SUCCESS -> {
                    loadingView.setVisibility(View.GONE);
                    ParseApiResponse res = parseApiResponseNetworkResources.getData();
                    learningDataModelArrayList.clear();
                    learningDataModelArrayList.addAll(res.getResults());
                    subHomeAdapter.notifyDataSetChanged();
                }
                case ERROR -> {
                    loadingView.setVisibility(View.GONE);
                    Toast.makeText(SubActivity.this, parseApiResponseNetworkResources.getMessage(), Toast.LENGTH_SHORT).show();
                }
                default -> {
                    loadingView.setVisibility(View.GONE);
                }
            }
        });
    }


    SubHomeAdapter subHomeAdapter;
    RecyclerView rvSubHome;

    private void setRvAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rvSubHome.setLayoutManager(gridLayoutManager);
        subHomeAdapter = new SubHomeAdapter(context, apiEndPoint, learningDataModelArrayList,position,TYPE,this);
        rvSubHome.setAdapter(subHomeAdapter);
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
