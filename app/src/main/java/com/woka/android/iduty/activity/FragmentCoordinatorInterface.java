package com.woka.android.iduty.activity;

import java.io.Serializable;

public interface FragmentCoordinatorInterface extends Serializable{

    void changeFragment(String id, int fragment);
}
