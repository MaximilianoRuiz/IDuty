package com.woka.android.iduty.entity;

public class User {

    private String uid;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private String type;
    private String address;
    private String facebook;
    private String password;
    private String birth;
    private int id;
    private int age;
    private int tel;
    private int cel;

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.picture = "";
        this.type = "";
        this.address = "";
        this.facebook = "";
        this.password = "";
        this.birth = "";
        this.id = 0;
        this.age = 0;
        this.tel = 0;
        this.cel = 0;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getCel() {
        return cel;
    }

    public void setCel(int cel) {
        this.cel = cel;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public boolean isUserDataCompleted() {
        boolean status = true;

        if (this.getFirstName() == null || this.getLastName() == null || this.getEmail() == null ||
                this.getAddress() == null || this.getBirth() == null || this.getId() == 0) {
            status = false;
        }
        return status;
    }
}
