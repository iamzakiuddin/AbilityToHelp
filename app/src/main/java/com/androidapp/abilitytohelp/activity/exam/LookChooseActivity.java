package com.androidapp.abilitytohelp.activity.exam;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.abilitytohelp.activity.SubActivity;
import com.androidapp.abilitytohelp.customclasses.AppControl;
import com.androidapp.abilitytohelp.interfaces.AdsCallback;
import com.androidapp.abilitytohelp.interfaces.CorrectAnswerCallback;
import com.androidapp.abilitytohelp.model.parseapismodels.ParseApiResponse;
import com.androidapp.abilitytohelp.model.parseapismodels.Result;
import com.androidapp.abilitytohelp.utils.CommonConstantAd;
import com.androidapp.abilitytohelp.utils.Utils;
import com.androidapp.abilitytohelp.viewmodels.ParseApisViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.adapter.ExamQuestionAdapter;
import com.androidapp.abilitytohelp.model.LearningDataModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LookChooseActivity extends AppCompatActivity implements CorrectAnswerCallback, AdsCallback {

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    LinearLayout backBtn;
    LinearLayout nextBtn;
    ImageView iVQuestion;
    int correctPosition;
    Random random;
    TextView txtTitleSubHome;
    ArrayList<ArrayList<LearningDataModel>> arrOfPrevious = new ArrayList<>();
    RecyclerView rv_exam;
    public List<Result> examQuestionAnswerList;
    ExamQuestionAdapter examQuestionAdapter;
    boolean isPreviousShow = false;
    private String apiEndPoint;
    ParseApisViewModel parseApisViewModel;
    ProgressBar loadingView;
    List<Result> learningDataModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);
        getSupportActionBar().hide();
        context = this;
        parseApisViewModel = new ViewModelProvider(this).get(ParseApisViewModel.class);
        initDefine();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomArray();
            }
        });
    }
    private void initDefine() {
        loadingView = findViewById(R.id.loading);
        iVQuestion = findViewById(R.id.iVQuestion);
        rv_exam = findViewById(R.id.rv_exam);
        txtTitleSubHome = findViewById(R.id.txtTitleSubHome);
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rv_exam.setLayoutManager(gridLayoutManager);
        Intent intent = getIntent();
        txtTitleSubHome.setText(intent.getStringExtra("SubCate"));
        apiEndPoint = intent.getStringExtra("desc_data");
        parseApisViewModel.hitParseApi(apiEndPoint);
        parseApisViewModel.getParseResponseObserver().observe(this, parseApiResponseNetworkResources -> {
            switch (Objects.requireNonNull(parseApiResponseNetworkResources.getStatus())) {
                case LOADING -> loadingView.setVisibility(View.VISIBLE);

                case SUCCESS -> {
                    loadingView.setVisibility(View.GONE);
                    ParseApiResponse res = parseApiResponseNetworkResources.getData();
                    learningDataModelArrayList.clear();
                    learningDataModelArrayList.addAll(res.getResults());
                    getRandomArray();
                }
                case ERROR -> {
                    loadingView.setVisibility(View.GONE);
                    Toast.makeText(LookChooseActivity.this, parseApiResponseNetworkResources.getMessage(), Toast.LENGTH_SHORT).show();
                }
                default -> {
                    loadingView.setVisibility(View.GONE);
                }
            }
        });
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
        CommonConstantAd.googlebeforloadAd(this);
    }




    private void getRandomArray() {
        random = new Random();
        examQuestionAnswerList = new ArrayList<>();
        correctPosition = random.nextInt(learningDataModelArrayList.size());
        examQuestionAnswerList.add(learningDataModelArrayList.get(correctPosition));
        Glide.with(context)
                .load(learningDataModelArrayList.get(correctPosition).getImage().getUrl())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iVQuestion);

        do {
            int number = random.nextInt(learningDataModelArrayList.size());
            if (!examQuestionAnswerList.contains(learningDataModelArrayList.get(number))) {
                examQuestionAnswerList.add(learningDataModelArrayList.get(number));
                examQuestionAnswerList.get(examQuestionAnswerList.size() - 1).setTrue(0);
            }
        }
        while (examQuestionAnswerList.size() != 4);
        Collections.shuffle(examQuestionAnswerList);
        setRvAdapter(isPreviousShow);
    }




    private void setRvAdapter(boolean isPreviousShow) {
        if (isPreviousShow) {
            Result learningDataModel = null;
            for (int i = 0; i < examQuestionAnswerList.size(); i++) {
                if (examQuestionAnswerList.get(i).isTrue()==1) {
                    learningDataModel = examQuestionAnswerList.get(i);
                    Glide.with(context)
                            .load(learningDataModel.getImage().getUrl())
                            .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into(iVQuestion);
                    break;
                }
            }
            examQuestionAdapter = new ExamQuestionAdapter(context, examQuestionAnswerList, learningDataModel,this);
        } else {
            examQuestionAdapter = new ExamQuestionAdapter(context, examQuestionAnswerList, learningDataModelArrayList.get(correctPosition),this);
        }
        rv_exam.setAdapter(examQuestionAdapter);
    }

    public void onClickNext(View view) {
        //getRandomArray();
    }




    public void onClickPrev(View view) {
        getRandomArray();
    }


    public void onClickBack(View view) {
        onBackPressed();
    }

    @Override
    public void onAnswerSelected() {
        getRandomArray();
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppControl.textToSpeech.setOnUtteranceProgressListener(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommonConstantAd.showInterstitialAdsGoogle(this,this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppControl.textToSpeech.stop();
    }

    @Override
    public void adLoadingFailed() {

    }

    @Override
    public void adClose() {

    }

    @Override
    public void startNextScreen() {

    }

    @Override
    public void onLoaded() {

    }
}
