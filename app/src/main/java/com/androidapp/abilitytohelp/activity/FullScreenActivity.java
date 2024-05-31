package com.androidapp.abilitytohelp.activity;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.abilitytohelp.interfaces.AdsCallback;
import com.androidapp.abilitytohelp.model.parseapismodels.Result;
import com.androidapp.abilitytohelp.utils.CommonConstantAd;
import com.androidapp.abilitytohelp.viewmodels.ParseApisViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.customclasses.AppControl;
import com.androidapp.abilitytohelp.customclasses.Constant;
import com.androidapp.abilitytohelp.customclasses.NonSwipeAbleViewPager;
import com.androidapp.abilitytohelp.model.LearningDataModel;
import com.androidapp.abilitytohelp.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends AppCompatActivity implements AdsCallback {

    Context context;
    int categoryPosition, selectedPosition;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    private String category;
    ParseApisViewModel parseApisViewModel;
    ImageView imgBtnPrev,imgBtnNext;
    List<Result> learningDataModelArrayList = new ArrayList<>();
    NonSwipeAbleViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        getSupportActionBar().hide();
        context = this;
        parseApisViewModel = new ViewModelProvider(this).get(ParseApisViewModel.class);
        initDefine();
        CommonConstantAd.googlebeforloadAd(this);
    }



    TextView tvItemName;
    LinearLayout backBtn;

    private void initDefine() {
        viewPager = findViewById(R.id.viewPager);
        tvItemName = findViewById(R.id.tvItemName);
        imgBtnNext = findViewById(R.id.imgBtnNext);
        imgBtnPrev = findViewById(R.id.imgBtnPrev);
        backBtn = findViewById(R.id.backBtn);

        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        categoryPosition = intent.getIntExtra("categoryPosition", 0);
        selectedPosition = intent.getIntExtra("selectedPosition", 0);
        learningDataModelArrayList = intent.getExtras().getParcelableArrayList("dataList");
        setViewPagerAdapter(learningDataModelArrayList);
    }

    private void setViewPagerAdapter(List<Result> learningDataModelArrayList) {
        //this.arrayOfImages = learningDataModelArrayList.fi;
        viewPagerAdapter = new ViewPagerAdapter(learningDataModelArrayList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(selectedPosition);
        tvItemName.setText(learningDataModelArrayList.get(selectedPosition).getTitle());
        tvItemName.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotation));
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(tvItemName);
        if (Utils.getPref(Constant.SOUND, true)) {
            AppControl.textToSpeech.speak(learningDataModelArrayList.get(selectedPosition).getDescData(), TextToSpeech.QUEUE_FLUSH, null);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                tvItemName.setText(arrayOfImages.get(i).showTitle);
            }

            @Override
            public void onPageSelected(int i) {
                tvItemName.setText(learningDataModelArrayList.get(i).getTitle());
                YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(tvItemName);
                if (Utils.getPref(Constant.SOUND, true)) {
                    AppControl.textToSpeech.speak(learningDataModelArrayList.get(i).getDescData(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void onClickBack(View view) {
        onBackPressed();
    }

    public void onClickPrev(View view) {
        if (viewPager.getCurrentItem() > 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void onClickNext(View view) {
        if (viewPager.getCurrentItem() < learningDataModelArrayList.size() - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }


    ImageView imageView;

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

    class ViewPagerAdapter extends PagerAdapter {

        List<Result> arrayOfImages;
        LayoutInflater inflater;

        ViewPagerAdapter(List<Result> arrayOfImages) {
            this.arrayOfImages = arrayOfImages;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return arrayOfImages.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View itemView = inflater.inflate(R.layout.viewpager_layout, container, false);
            imageView = itemView.findViewById(R.id.cellImgViewPager);
            Glide.with(context)
                    .load(arrayOfImages.get(position).getImage().getUrl())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imageView);


//            imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bubble_anim));

            YoYo.with(Techniques.Tada).duration(1200).repeat(Animation.INFINITE).playOn(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((RelativeLayout) object);
        }

    }
    public void prepareDataForLearning(String categoryEndPoint) {
        //parseApisViewModel.hitParseApi(categoryEndPoint);
        setViewPagerAdapter(learningDataModelArrayList);
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
    }


}
