package com.mahmoudrefaie.sekkawahda.Pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    private String userID;
    @SerializedName("userName")
    private String userName;
    @SerializedName("profilePic")
    private String profilePicImage;
    @SerializedName("userEmailID")
    private String userEmailID;
    @SerializedName("userPassword")
    private String userPassword;
    @SerializedName("ssn")
    private String ssn;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("city")
    private String city;
    @SerializedName("grantType")
    private String grantType;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("driverTotalRate")
    private float driverTotalRate;
    @SerializedName("carImageUrl")
    private String carImageUrl;
    @SerializedName("driverLicense")
    private String driverLicense;
    @SerializedName("carLicense")
    private String carLicense;
    @SerializedName("carModel")
    private String carModel;
    @SerializedName("profile_type")
    private String profileType;

    public User(String userName, String userEmailID, String userPassword, String ssn, String phoneNumber, String city) {
        this.userName = userName;
        this.userEmailID = userEmailID;
        this.userPassword = userPassword;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public User(String userName, String userPassword, String grantType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.grantType = grantType;
    }

    public User(String userID,String fullName,float driverTotalRate,String city,String userEmailID,String ssn, String phoneNumber,
                String carImageUrl,String driverLicense,String carLicense,String carModel){
        this.userID = userID;
        this.fullName = fullName;
        this.driverTotalRate = driverTotalRate;
        this.city = city;
        this.userEmailID = userEmailID;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.carImageUrl = carImageUrl;
        this.driverLicense = driverLicense;
        this.carLicense = carLicense;
        this.carModel = carModel;
    }
    public User(String fullName , String city , String phoneNumber ,String userEmailID , String carModel){
        this.fullName = fullName;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.userEmailID = userEmailID;
        this.carModel = carModel;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicImage() {
        return profilePicImage;
    }

    public void setProfilePicImage(String profilePicImage) {
        this.profilePicImage = profilePicImage;
    }

    public String getUserEmailID() {
        return userEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        this.userEmailID = userEmailID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getDriverTotalRate() {
        return driverTotalRate;
    }

    public void setDriverTotalRate(float driverTotalRate) {
        this.driverTotalRate = driverTotalRate;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
}
