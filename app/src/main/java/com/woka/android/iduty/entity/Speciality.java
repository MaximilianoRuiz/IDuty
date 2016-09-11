package com.woka.android.iduty.entity;

import java.util.HashMap;

public class Speciality implements EntityInterface{

    private String id;
    private String name;

    public HashMap<String, Specialist> specialistList;

    public Speciality() {
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

    public HashMap<String, Specialist> getSpecialistList() {
        return specialistList;
    }

    public void setSpecialistList(HashMap<String, Specialist> specialistList) {
        this.specialistList = specialistList;
    }
}
