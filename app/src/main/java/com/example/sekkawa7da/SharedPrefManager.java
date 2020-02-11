package com.example.sekkawa7da;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static SharedPrefManager mInstance;
    private Context cont;

    private SharedPrefManager(Context cont) {
        this.cont = cont;
    }

    public static synchronized SharedPrefManager getInstance(Context cont){
        if(mInstance == null)
            mInstance = new SharedPrefManager(cont);
        return mInstance;
    }

    public void saveUser(LoginResponse loginResponse){
        SharedPreferences sharedPreferences = cont.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token",loginResponse.getAccess_token());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = cont.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getInt("expires_in" , -1 )!=-1)
            return true;
        return false;
    }

    public LoginResponse getResponse(){
        SharedPreferences sharedPreferences = cont.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        LoginResponse loginResponse = new LoginResponse(
                sharedPreferences.getString("access_token" , null ),
                sharedPreferences.getString("token_type" , null),
                sharedPreferences.getInt("expires_in" , -1)
        );
        return loginResponse;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = cont.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        User user = new User(
                sharedPreferences.getString("UserName" , null ),
                sharedPreferences.getString("UserEmailID" , null),
                sharedPreferences.getString("UserPassword" , null),
                sharedPreferences.getString("SSN" , null),
                sharedPreferences.getString("phoneNumber" , null),
                sharedPreferences.getString("city" , null)
        );
        return user;
    }

    public void clear(){
        SharedPreferences sharedPreferences = cont.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}