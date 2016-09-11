package com.woka.android.iduty.entity;

import java.util.HashMap;

public class Specialist implements EntityInterface{

    private String id;
    private String name;

    public Specialist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
