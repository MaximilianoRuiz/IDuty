package com.woka.android.iduty.fragment;

import android.app.Activity;

import com.facebook.login.widget.LoginButton;

public interface FragmentInteractorInterface {

    Activity getActivityInstance();
    LoginButton getLoginButton();
}
