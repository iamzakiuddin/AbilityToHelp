package com.androidapp.abilitytohelp.activity;

import android.content.Context;
import android.content.Intent;
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
import com.androidapp.abilitytohelp.adapter.HomeCategoriesAdapter;
import com.androidapp.abilitytohelp.model.parseapismodels.ParseApiResponse;
import com.androidapp.abilitytohelp.model.parseapismodels.Result;
import com.androidapp.abilitytohelp.network.NetworkResources;
import com.androidapp.abilitytohelp.utils.Utils;
import com.androidapp.abilitytohelp.viewmodels.ParseApisViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    LinearLayout backBtn;
    ParseApisViewModel parseApisViewModel;
    ProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

        parseApisViewModel.mgetCategoriesObserver().observe(this, parseApiResponseNetworkResources -> {
            switch (Objects.requireNonNull(parseApiResponseNetworkResources.getStatus())) {
                case LOADING -> loadingView.setVisibility(View.VISIBLE);

                case SUCCESS -> {
                    loadingView.setVisibility(View.GONE);
                    ParseApiResponse res = parseApiResponseNetworkResources.getData();
                    homeCategoryTitles.clear();
                    homeCategoryTitles.addAll(res.getResults());
                    homeCategoriesAdapter.notifyDataSetChanged();
                }
                case ERROR -> {
                    loadingView.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, parseApiResponseNetworkResources.getMessage(), Toast.LENGTH_SHORT).show();
                }
                default -> {
                    loadingView.setVisibility(View.GONE);
                }
            }
        });
    }



    public int[] mainCategoryList = new int[]{};
    //String[] homeCategoryTitles = new String[]{};
    List<Result> homeCategoryTitles = new ArrayList<>();
    TextView txtExamTitle;
    int type=1;
    private void initDefine() {
        backBtn = findViewById(R.id.backBtn);
        rvHomeCategories = findViewById(R.id.rvHomeCategories);
        txtExamTitle = findViewById(R.id.txtTitleSubHome);
        loadingView = findViewById(R.id.loadingView);

        Intent intent=getIntent();
        type=intent.getIntExtra("Type",1);
        if(type==1){
            txtExamTitle.setText(context.getText(R.string.kids_learning));
        }else if(type==2){
            txtExamTitle.setText(context.getText(R.string.look_and_choose));
        }else if(type==3){
            txtExamTitle.setText(context.getText(R.string.listen_and_guess));
        }
        parseApisViewModel.mgetCategoriesResponse();
        //homeCategoryTitles = new String[]{"Alphabets", "Numbers", "Colors", "Shapes", "Animals", "Birds", "Flowers", "Fruits", "Month", "Vegetable", "Body Parts", "Clothes", "Country", "Food", "Geometry", "House", "Jobs", "School", "Sports", "Vehicle", "Daily Routine", "Face Expressions"};
        //mainCategoryList = new int[]{R.drawable.home_alphabet, R.drawable.home_number, R.drawable.home_color, R.drawable.home_shape, R.drawable.home_animal, R.drawable.home_birds, R.drawable.home_flower, R.drawable.home_fruits, R.drawable.home_month, R.drawable.home_vegetable, R.drawable.home_body_parts, R.drawable.home_clothes, R.drawable.home_country, R.drawable.home_food, R.drawable.home_geometry, R.drawable.home_house, R.drawable.home_jobs, R.drawable.home_school, R.drawable.home_sports, R.drawable.home_vehicle, R.drawable.routines,R.drawable.emoji};
        setRvAdapter();
    }


    HomeCategoriesAdapter homeCategoriesAdapter;
    RecyclerView rvHomeCategories;

    private void setRvAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rvHomeCategories.setLayoutManager(gridLayoutManager);
        homeCategoriesAdapter = new HomeCategoriesAdapter(context, mainCategoryList,homeCategoryTitles,type,this);
        rvHomeCategories.setAdapter(homeCategoriesAdapter);
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
