package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.customclasses.Constant;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils;

import static com.apps.abilitytohelp.kidslearning.kidseducation.preschool.customclasses.Constant.switchState;

public class SettingActivity extends AppCompatActivity {
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();
        initDefine();
        llAdView = findViewById(R.id.llAdView);
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook);
        //Utils.loadBannerAd(this,llAdView,llAdViewFacebook);
    }


    SwitchCompat soundOnOff;
    private void initDefine() {
        soundOnOff=findViewById(R.id.soundOnOff);
        if(Utils.getPref(Constant.SOUND,true)){
            soundOnOff.setChecked(true);
            switchState=true;
        }else{
            switchState=false;
            soundOnOff.setChecked(false);
        }
        soundOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchState){
                    switchState=false;
                    soundOnOff.setChecked(false);
                    Utils.setPref(Constant.SOUND,false);
                }else {
                    switchState=true;
                    soundOnOff.setChecked(true);
                    Utils.setPref(Constant.SOUND,true);
                }
            }
        });

    }

    public void onClickBack(View view) {
        finish();
    }





    public void shareApp(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        String link = "https://play.google.com/store/apps/details?id=" + getPackageName();
        shareIntent.putExtra(Intent.EXTRA_TEXT, link);
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.app_name)));
    }


    public void ContactUs(View view) {
        try {
            Intent sendIntentGmail = new Intent(Intent.ACTION_SEND);
            sendIntentGmail.setType("plain/text");
            sendIntentGmail.setPackage("com.google.android.gm");
            sendIntentGmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"Enter your email"});
            if (getResources().getString(R.string.app_name) != null)
                sendIntentGmail.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name)+" - Android");
            startActivity(sendIntentGmail);
        } catch (Exception e) {
            Intent sendIntentIfGmailFail = new Intent(Intent.ACTION_SEND);
            sendIntentIfGmailFail.setType("*/*");
            sendIntentIfGmailFail.putExtra(Intent.EXTRA_EMAIL, new String[]{"Enter your email"});
            if (getResources().getString(R.string.app_name) != null)
                sendIntentIfGmailFail.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name)+" - Android");
            if (sendIntentIfGmailFail.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntentIfGmailFail);
            }
        }
    }

    public void RateUs(View view) {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public void MoreApp(View view) {
        Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Ninety+Nine+Apps");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void PrivacyPolicy(View view) {
        startActivity(new Intent(SettingActivity.this,PrivacyPolicyActivity.class));
    }
}
