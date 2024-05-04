package com.androidapp.abilitytohelp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.androidapp.abilitytohelp.customclasses.Constant;
import com.androidapp.abilitytohelp.interfaces.AdsCallback;

public class CommonConstantAd {

    private static AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    static InterstitialAd googleinterstitial;

    public static void googlebeforloadAd(Context context) {
        try {
            InterstitialAd.load(context, Constant.GOOGLE_INTERSTITIAL_ID, getAdRequest(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            super.onAdLoaded(interstitialAd);
                            googleinterstitial = interstitialAd;
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            googleinterstitial = null;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void showInterstitialAdsGoogle(Activity activity, final AdsCallback adsCallback) {
        try {
            if (googleinterstitial != null) {
                googleinterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        googleinterstitial = null;
                        adsCallback.startNextScreen();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        adsCallback.onLoaded();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        googleinterstitial = null;
                        adsCallback.startNextScreen();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });

                googleinterstitial.show(activity);
            } else {
                adsCallback.startNextScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void loadBannerGoogleAd(Context context, final RelativeLayout llAdview) {
        /*RequestConfiguration conf= new RequestConfiguration.Builder().setTagForChildDirectedTreatment(1).build();
        MobileAds.setRequestConfiguration(conf);*/
        MobileAds.initialize(context, initializationStatus -> { });
        final com.google.android.gms.ads.AdView adViewBottom = new com.google.android.gms.ads.AdView(context);
        adViewBottom.setAdSize(AdSize.BANNER);
        adViewBottom.setAdUnitId(Constant.GOOGLE_BANNER_ID);
        llAdview.addView(adViewBottom);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewBottom.loadAd(adRequest);
        adViewBottom.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adViewBottom.setVisibility(View.VISIBLE);
                llAdview.setVisibility(View.VISIBLE);
                // Code to be executed when an ad finishes loading.
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                llAdview.setVisibility(View.VISIBLE);
            }
        });
    }

}
