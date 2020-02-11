package com.example.sekkawa7da;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("UserID")
    @Expose
    private String UserID;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("UserEmailID")
    @Expose
    private String UserEmailID;
    @SerializedName("UserPassword")
    @Expose
    private String UserPassword;
    @SerializedName("SSN")
    @Expose
    private String SSN;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("grant_type")
    @Expose
    private String grant_type;


    public User(String userName, String userEmailID, String userPassword, String SSN, String phoneNumber, String city) {
        UserName = userName;
        UserEmailID = userEmailID;
        UserPassword = userPassword;
        this.SSN = SSN;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public User(String userName, String userPassword, String grant_type) {
        UserName = userName;
        UserPassword = userPassword;
        this.grant_type = grant_type;
    }

    public User(String userName) {
        UserName = userName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmailID() {
        return UserEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        UserEmailID = userEmailID;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
