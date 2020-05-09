package com.example.sekkawa7da;

import android.util.Log;

import java.util.ArrayList;

public class OurData {

    private static final String TAG = "OurData";

    public static int[] mImages = new int[] {
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo
    };

    public static String[] mUsernames = new String[]{
            "Mahmoud Refaie","Mahmoud Refaie","Ahmed Ali","Mahmoud Refaie","Mahmoud Refaie","Mahmoud Refaie"
    };
    public static String[] mDurations = new String[]{
            "3 mins ago","2 days ago","3 mins ago","3 mins ago","3 mins ago","3 mins ago"
    };
    public static String[] mFromCity = new String[]{
            "Luxor","Assiut","Aswan","Aswan","Aswan","Aswan"
    };
    public static String[] mToCity = new String[]{
            "Cairo","Aswan","Assiut","Assiut","Assiut","Assiut"
    };
    public static String[] mTripDate = new String[]{
            "3 May","4 May","3 May","3 May","3 May","3 May"
    };
    public static String[] mTripTime = new String[]{
            "1:00 PM","10:00 AM","1:00 PM","1:00 PM","1:00 PM","1:00 PM"
    };
    public static String[] mDiscription = new String[]{
            "This is the first trip","This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip ","This is the first trip ","This is the first trip ","This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip ","This is the first trip "
    };



    /*
    public ArrayList<Integer> mImages = new ArrayList<>();
    public ArrayList<String> mUsernames = new ArrayList<>();
    public ArrayList<String> mDurations = new ArrayList<>();
    public ArrayList<String> mFromCity = new ArrayList<>();
    public ArrayList<String> mToCity = new ArrayList<>();
    public ArrayList<String> mTripDate = new ArrayList<>();
    public ArrayList<String> mTripTime = new ArrayList<>();
    public ArrayList<String> mDiscription = new ArrayList<>();

    private void initPostBitMaps(){
        Log.d(TAG,"initPostBitMaps: preparing bitmaps.");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip " +
                "This is the first trip This is the first trip This is the first trip ");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip This is the first trip This is the first trip This is the first trip " +
                "This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip " +
                "This is the first trip This is the first trip This is the first trip This is the first trip This is the first trip ");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

        mImages.add(R.drawable.logo);
        mUsernames.add("Mahmoud Refaie");
        mDurations.add("3 mins ago");
        mFromCity.add("Aswan");
        mToCity.add("Assiut");
        mTripDate.add("3 Abr");
        mTripTime.add("1:00 PM");
        mDiscription.add("This is the first trip");

    }
     */
}
