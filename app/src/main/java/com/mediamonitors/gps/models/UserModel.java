package com.mediamonitors.gps.models;

public class UserModel {


    String id;
    String name;
    String userLoginID;
    String password;
   String message;

    public UserModel(String id, String name, String userLoginID, String password, String message) {
        this.id = id;
        this.name = name;
        this.userLoginID = userLoginID;
        this.password = password;
        this.message = message;
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

    public String getUserLoginID() {
        return userLoginID;
    }

    public void setUserLoginID(String userLoginID) {
        this.userLoginID = userLoginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public String getMessage(){ return message;}
    public void setMessage(String message){this.message=message;}







}
