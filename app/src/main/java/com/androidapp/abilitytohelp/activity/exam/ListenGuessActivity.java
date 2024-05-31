package com.androidapp.abilitytohelp.activity.exam;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.abilitytohelp.interfaces.AdsCallback;
import com.androidapp.abilitytohelp.interfaces.CorrectAnswerCallback;
import com.androidapp.abilitytohelp.model.parseapismodels.ParseApiResponse;
import com.androidapp.abilitytohelp.model.parseapismodels.Result;
import com.androidapp.abilitytohelp.utils.CommonConstantAd;
import com.androidapp.abilitytohelp.viewmodels.ParseApisViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.adapter.ListenGuessAdapter;
import com.androidapp.abilitytohelp.customclasses.AppControl;
import com.androidapp.abilitytohelp.customclasses.Constant;
import com.androidapp.abilitytohelp.model.LearningDataModel;
import com.androidapp.abilitytohelp.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ListenGuessActivity extends AppCompatActivity implements CorrectAnswerCallback, AdsCallback {

    Context context;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    LinearLayout backBtnn;
    LinearLayout nextBtn;
    RecyclerView rv_exam;
    public List<Result> examQuestionAnswerList;
    ListenGuessAdapter listenGuessAdapter;
    ImageView iVQuestion;
    TextView tvName;
    int correctPosition;
    Random random;
    //    TextToSpeech textToSpeech;
    TextView txtTitleSubHome;
    ProgressBar loadingView;
    List<Result> learningDataModelArrayList = new ArrayList<>();
    private String apiEndPoint;
    ParseApisViewModel parseApisViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_listen_guess);
        getSupportActionBar().hide();
        context = this;
        parseApisViewModel = new ViewModelProvider(this).get(ParseApisViewModel.class);
        initDefine();
        CommonConstantAd.googlebeforloadAd(this);
    }
    private void initDefine() {
        loadingView = findViewById(R.id.loading);
        iVQuestion = findViewById(R.id.iVQuestion);
        rv_exam = findViewById(R.id.rv_exam);
        tvName = findViewById(R.id.tvName);
        txtTitleSubHome = findViewById(R.id.txtTitleSubHome);
        backBtnn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);

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
                    Toast.makeText(ListenGuessActivity.this, parseApiResponseNetworkResources.getMessage(), Toast.LENGTH_SHORT).show();
                }
                default -> {
                    loadingView.setVisibility(View.GONE);
                }
            }
        });
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
        CommonConstantAd.googlebeforloadAd(this);
        backBtnn.setOnClickListener(new View.OnClickListener() {
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


    private void getRandomArray() {
        random = new Random();
        examQuestionAnswerList = new ArrayList<>();
        correctPosition = random.nextInt(learningDataModelArrayList.size());
        if (Utils.getPref(Constant.SOUND,true)) {
            AppControl.textToSpeech.speak(learningDataModelArrayList.get(correctPosition).getTitle(), TextToSpeech.QUEUE_FLUSH, null);
        }
        tvName.setText(learningDataModelArrayList.get(correctPosition).getDescData());
        Glide.with(context)
                .load(R.drawable.btn_sound)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iVQuestion);
        iVQuestion.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce_amn));
        examQuestionAnswerList.add(learningDataModelArrayList.get(correctPosition));
        do {
            int number = random.nextInt(learningDataModelArrayList.size());
            if (!examQuestionAnswerList.contains(learningDataModelArrayList.get(number))) {
                examQuestionAnswerList.add(learningDataModelArrayList.get(number));
            }
        }
        while (examQuestionAnswerList.size() != 4);
        Collections.shuffle(examQuestionAnswerList);
        setRvAdapter();
    }

    public void onClickSound(View view) {
        if (Utils.getPref(Constant.SOUND,true)) {
            AppControl.textToSpeech.speak(learningDataModelArrayList.get(correctPosition).getTitle(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    private void setRvAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        rv_exam.setLayoutManager(gridLayoutManager);
        listenGuessAdapter = new ListenGuessAdapter(context, examQuestionAnswerList, learningDataModelArrayList.get(correctPosition),this);
        rv_exam.setAdapter(listenGuessAdapter);
    }

    public void onClickNext(View view) {
        getRandomArray();
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
