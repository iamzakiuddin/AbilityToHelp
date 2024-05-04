package com.androidapp.abilitytohelp.interfaces;

public interface AdsCallback {

    void adLoadingFailed();

    void adClose();

    void startNextScreen();

    void onLoaded();
}