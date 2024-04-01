package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Handler;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.interfaces.CallbackListener;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;

public class SplashActivity extends AppCompatActivity implements CallbackListener {
    private boolean isComingFromSettings = false;
    private AppUpdateManager appUpdateManager;

    /*For Internet*/
    @Override
    public void onSuccess() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onRetry() {
        //callApi();
    }


    Context context;
    private ActivityResultLauncher<String> notificationsPermissionLauncher;
    Boolean hasNotificationPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        appUpdateManager = AppUpdateManagerFactory.create(this);
        notificationsPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (!isGranted) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (Build.VERSION.SDK_INT >= 33) {
                                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                                        showNotificationPermissionRationale();
                                    } else {
                                        showSettingDialog();
                                    }
                                }
                            }
                        } else {
                            navigateToMainMenu();
                        }
                    }
                });
                context = this;
        callApi();

    }

    private void showSettingDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Notification permission is required, Please allow notification permission from setting")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isComingFromSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        navigateToMainMenu();
                    }
                })
                .show();
    }

    public void callApi() {
        if (Utils.isNetworkConnected(this)) {
            checkForAppUpdates();
        } else {
            Utils.openInternetDialog(this, true,this);
        }
    }

    private void successCall() {
        startNextActivity(2000);
    }

    public void startNextActivity(Integer time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (notificationsPermissionLauncher!=null){
                    if (Build.VERSION.SDK_INT >= 33) {
                        notificationsPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
                    } else {
                        hasNotificationPermissionGranted = true;
                    }
                    if (hasNotificationPermissionGranted){
                        navigateToMainMenu();
                    }
                }
            }
        }, time);
    }

    private void navigateToMainMenu() {
        Intent intent = new Intent(SplashActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    private void showNotificationPermissionRationale(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Ability to Help app needs permission to send daily notification. If denied, you won't receive daily notification. Thank you for understanding.")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notificationsPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        navigateToMainMenu();
                    }
                })
                .show();
    }

    private Boolean isLoaded = false;

    private Handler handler = new Handler();
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            if (Utils.isNetworkConnected(SplashActivity.this)) {
                if (!isLoaded) {
                    startNextActivity(0);
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (isComingFromSettings){
            isComingFromSettings = false;
            navigateToMainMenu();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(myRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationsPermissionLauncher = null;
        handler.removeCallbacks(myRunnable);
    }

    private void checkForAppUpdates(){
        Task<AppUpdateInfo> appUpdateInfoTask  =  appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                try {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.IMMEDIATE,this,123);
                } catch (IntentSender.SendIntentException e) {
                    successCall();
                }
            }else{
                successCall();
            }
        });
        appUpdateInfoTask.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Failure message: ",e.getLocalizedMessage());
                successCall();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123){
            if (resultCode!=RESULT_OK){
                Log.e("App update","Something went wrong");
                finish();
            }
        }
    }
}

