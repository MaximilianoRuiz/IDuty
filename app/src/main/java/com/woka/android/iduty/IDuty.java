package com.woka.android.iduty;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.woka.android.iduty.activity.FragmentCoordinatorInterface;
import com.woka.android.iduty.data.FirebaseLoginManager;
import com.woka.android.iduty.entity.Clinic;
import com.woka.android.iduty.entity.Turn;
import com.woka.android.iduty.entity.User;

public class IDuty extends Application {

    public static IDuty APPLICATION;
    private FirebaseLoginManager firebaseLoginManager;
    private FragmentCoordinatorInterface coordinatorInterface;

    private User user;
    private Turn turn;
    private Clinic myClinic;

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

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public void createTurn() {
        turn = new Turn();
    }

    public Clinic getMyClinic() {
        return myClinic;
    }

    public void setMyClinic(Clinic myClinic) {
        this.myClinic = myClinic;
    }

    public FragmentCoordinatorInterface getCoordinatorInterface() {
        return coordinatorInterface;
    }

    public void setCoordinatorInterface(FragmentCoordinatorInterface coordinatorInterface) {
        this.coordinatorInterface = coordinatorInterface;
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

    public void setClinic(Clinic  clinic) {
        firebaseLoginManager.setClinic(clinic);
    }

    public void sendTurn(Turn turn) {
        firebaseLoginManager.sendTurn(turn);
    }
}
