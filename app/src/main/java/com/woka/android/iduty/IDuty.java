package com.woka.android.iduty;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.woka.android.iduty.data.FirebaseLoginManager;
import com.woka.android.iduty.entity.User;

public class IDuty extends Application {

    public static IDuty APPLICATION;
    private User user;
    private FirebaseLoginManager firebaseLoginManager;

    private boolean isMainActivityRunning;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        firebaseLoginManager = new FirebaseLoginManager();
        APPLICATION = this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FirebaseLoginManager getFirebaseLoginManager() {
        return firebaseLoginManager;
    }

    public boolean isMainActivityRunning() {
        return isMainActivityRunning;
    }

    public void setIsMainActivityRunning(boolean isMainActivityRunning) {
        this.isMainActivityRunning = isMainActivityRunning;
    }
}
