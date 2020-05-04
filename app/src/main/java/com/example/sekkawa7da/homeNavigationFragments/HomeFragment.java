package com.example.sekkawa7da.homeNavigationFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sekkawa7da.PostsListAdapter;
import com.example.sekkawa7da.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    /*
    private ArrayList<Integer> mImages = new ArrayList<>();
    private ArrayList<String> mUsernames , mDurations , mFromCity , mToCity , mTripDate , mTripTime , mDiscription
            = new ArrayList<>();
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.posts_recycler);
        PostsListAdapter adapter = new PostsListAdapter(/*getContext(),mImages,mUsernames,mDurations,mFromCity,mToCity,mTripDate,mTripTime,mDiscription*/);
        recycler.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        /*
        Log.d(TAG,"onCreateView: started");
        try {
            initPostBitMaps();
        }catch (Exception e){
            e.getMessage();
        }
        */

        return view;
    }

}
