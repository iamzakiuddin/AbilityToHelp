package com.androidapp.abilitytohelp.customclasses;

import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.androidapp.abilitytohelp.R;
import com.androidapp.abilitytohelp.utils.Utils;
import com.parse.Parse;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppControl extends Application {
    Context context;
    public static TextToSpeech textToSpeech;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        new Utils(context);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Fon/OhWhale.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.UK);
//                    textToSpeech.setLanguage(new Locale("tr","TR"));

                }
            }

        });
        textToSpeech.setSpeechRate(0.3f);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.PARSE_APP_ID))
                .clientKey(getString(R.string.PARSE_CLIENT_KEY))
                .server("https://parseapi.back4app.com/")
                .build());
    }

}
