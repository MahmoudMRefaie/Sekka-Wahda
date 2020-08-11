package com.mahmoudrefaie.sekkawahda.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "intro";
    private final String USERNAME = "UserName";
    private final String EMAIL = "UserEmailID";
    private final String SSN = "SSN";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putUserName(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERNAME, loginorout);
        edit.commit();
    }
    public String getUserName() {
        return app_prefs.getString(USERNAME, "");
    }

    public void putEmail(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(EMAIL, loginorout);
        edit.commit();
    }
    public String getEmail() {
        return app_prefs.getString(EMAIL, "");
    }

    public void putSSN(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(SSN, loginorout);
        edit.commit();
    }
    public String getSSN() {
        return app_prefs.getString(SSN, "");
    }
}
