package com.woka.android.iduty.entity;

public class Clinic implements EntityInterface{

    private String id;
    private String name;

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

    @Override
    public String getEntityId() {
        return id;
    }

    @Override
    public String getEntityName() {
        return name;
    }
}
