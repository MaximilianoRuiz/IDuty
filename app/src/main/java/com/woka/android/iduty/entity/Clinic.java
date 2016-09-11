package com.woka.android.iduty.entity;

import java.util.HashMap;

public class Clinic implements EntityInterface{

    private String id;
    private String name;

    public HashMap<String, Speciality> specialitieList;

    public Clinic() {
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

    public HashMap<String, Speciality> getSpecialitieList() {
        return specialitieList;
    }

    public void setSpecialitieList(HashMap<String, Speciality> specialities) {
        this.specialitieList = specialities;
    }

}
