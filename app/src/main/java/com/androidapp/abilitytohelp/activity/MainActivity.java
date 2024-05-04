package com.androidapp.abilitytohelp.activity;

import android.content.Context;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.activity.video.VideoLearningActivity;
import com.androidapp.abilitytohelp.adapter.HomeAdapter;
import com.androidapp.abilitytohelp.customclasses.Constant;
import com.androidapp.abilitytohelp.database.DatabaseHelper;
import com.androidapp.abilitytohelp.interfaces.AdsCallback;
import com.androidapp.abilitytohelp.interfaces.CallbackListener;
import com.androidapp.abilitytohelp.utils.Utils;

public class MainActivity extends AppCompatActivity implements CallbackListener, AdsCallback {

    Context context;
    DatabaseHelper databaseHelper;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        context=this;
        initDefine();
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        successCall();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
    }


    private void successCall() {
        if (Utils.isNetworkConnected(this)) {
            /*if (Constant.ENABLE_DISABLE.equals(Constant.ENABLE)) {

                Utils.setPref(MainActivity.this, Constant.AD_TYPE_FB_GOOGLE, Constant.AD_TYPE_FACEBOOK_GOOGLE);
                Utils.setPref(MainActivity.this, Constant.GOOGLE_BANNER, Constant.GOOGLE_BANNER_ID);
                Utils.setPref(MainActivity.this, Constant.GOOGLE_INTERSTITIAL, Constant.GOOGLE_INTERSTITIAL_ID);
                Utils.setPref(MainActivity.this, Constant.STATUS_ENABLE_DISABLE, Constant.ENABLE_DISABLE);

                setAppAdId(Constant.GOOGLE_ADMOB_APP_ID);
            } else {
                Utils.setPref(MainActivity.this, Constant.STATUS_ENABLE_DISABLE, Constant.ENABLE_DISABLE);
            }*/
        } else {
            Utils.openInternetDialog(this, true,this);
        }
    }


    public void setAppAdId(String id) {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;
            String beforeChangeId = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
            Log.e("TAG", "setAppAdId:BeforeChange:::::  " + beforeChangeId);
            applicationInfo.metaData.putString("com.google.android.gms.ads.APPLICATION_ID", id);
            String AfterChangeId = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
            Log.e("TAG", "setAppAdId:AfterChange::::  " + AfterChangeId);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }

    }


    public int[] arrOfCategory;
    public int[] categoriesTitle;
    private void initDefine() {
        back = findViewById(R.id.back);
        rvCategory=findViewById(R.id.rvCategory);
        arrOfCategory = new int[]{R.drawable.card_one, R.drawable.card_two, R.drawable.card_three, R.drawable.card_four};
        categoriesTitle = new int[]{R.string.kids_learning, R.string.video_learning, R.string.look_and_choose, R.string.listen_and_guess};
        setRvAdapter();
    }


    public void onClickSetting(View view){
        Intent intent= new Intent(MainActivity.this,SettingActivity.class);
        startActivityForResult(intent,111);
    }

    HomeAdapter homeAdapter;
    RecyclerView rvCategory;
    int position = 0;
    private void setRvAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        rvCategory.setLayoutManager(gridLayoutManager);
        homeAdapter=new HomeAdapter(context, arrOfCategory,categoriesTitle, new HomeAdapter.onClickMain() {
            @Override
            public void onClickCategory(int pos) {
                position = pos;
               /* if (Utils.getPref(MainActivity.this, Constant.CLICK_IMAGE_COUNT, 1) == 1) {
                    Utils.setPref(MainActivity.this, Constant.CLICK_IMAGE_COUNT, 2);
                    startNextActivity();
                }else{
                    checkAd();
                }*/
                startNextActivity();
            }
        });
        rvCategory.setAdapter(homeAdapter);
    }

    public void checkAd(){
        if (Utils.getPref(MainActivity.this, Constant.STATUS_ENABLE_DISABLE, "").equals(Constant.ENABLE)) {
            if (Utils.getPref(MainActivity.this, Constant.AD_TYPE_FB_GOOGLE, "").equals(Constant.AD_GOOGLE)) {
                //CommonConstantAd.showInterstitialAdsGoogle(MainActivity.this,MainActivity.this);
                startNextActivity();
            }  else {
                startNextActivity();
            }
            Utils.setPref(MainActivity.this, Constant.CLICK_IMAGE_COUNT, 1);
        }else {
            startNextActivity();
        }
    }

    public void startNextActivity(){
        switch (position) {
            case 0:
                Intent intent1 = new Intent(context, HomeActivity.class);
                intent1.putExtra("Type", 1);
                context.startActivity(intent1);
                overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                break;
            case 1:
                Intent intent2 = new Intent(context, VideoLearningActivity.class);
                context.startActivity(intent2);
                overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                break;
            case 2:
                Intent intent3 = new Intent(context, HomeActivity.class);
                intent3.putExtra("Type", 2);
                context.startActivity(intent3);
                overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                break;
            case 3:
                Intent intent4 = new Intent(context, HomeActivity.class);
                intent4.putExtra("Type", 3);
                context.startActivity(intent4);
                overridePendingTransition(R.anim.enter_anim,R.anim.exit);
                break;
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onRetry() {

    }





    @Override
    public void adLoadingFailed() {
        startNextActivity();
    }

    @Override
    public void adClose() {
        startNextActivity();
    }

    @Override
    public void startNextScreen() {
        startNextActivity();
    }
    @Override
    public void onLoaded() {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_a,R.anim.slide_down_b);
    }
}
