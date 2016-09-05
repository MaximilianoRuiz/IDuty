package com.woka.android.iduty.entity;

import java.util.ArrayList;

public class Clinic implements EntityInterface{

    private String id;
    private String name;

    private ArrayList<Speciality> specialitieList;

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

    public ArrayList<Speciality> getSpecialitieList() {
        return specialitieList;
    }

    public void setSpecialitieList(ArrayList<Speciality> specialities) {
        this.specialitieList = specialities;
    }

    @Override
    public String getEntityId() {
        return id;
    }

    @Override
    public String getEntityName() {
        return name;
    }
}
