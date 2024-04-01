package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.interfaces;

public interface AdsCallback {

    void adLoadingFailed();

    void adClose();

    void startNextScreen();

    void onLoaded();
}