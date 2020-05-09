package com.example.sekkawa7da.MainPageNavigationFragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sekkawa7da.ui.PostsListAdapter;
import com.example.sekkawa7da.R;

import java.util.Calendar;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private TextView tripDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView tripTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private TextView writePost;
    private RelativeLayout extendedPost , createPost;
    private Button submitPost;
    private Spinner toCity , fromCity;


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

        //DatePicker Dialog
        tripDate = view.findViewById(R.id.date);
        tripDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(cal.YEAR);
                int month = cal.get(cal.MONTH);
                int day = cal.get(cal.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog,mDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String monthInAlpha="";
                switch (month){
                    case 1:
                        monthInAlpha = "Jan";
                        break;
                    case 2:
                        monthInAlpha = "Feb";
                        break;
                    case 3:
                        monthInAlpha = "Mar";
                        break;
                    case 4:
                        monthInAlpha = "Abr";
                        break;
                    case 5:
                        monthInAlpha = "May";
                        break;
                    case 6:
                        monthInAlpha = "Jun";
                        break;
                    case 7:
                        monthInAlpha = "Jul";
                        break;
                    case 8:
                        monthInAlpha = "Aug";
                        break;
                    case 9:
                        monthInAlpha = "Sep";
                        break;
                    case 10:
                        monthInAlpha = "Oct";
                        break;
                    case 11:
                        monthInAlpha = "Nov";
                        break;
                    case 12:
                        monthInAlpha = "Dec";
                        break;
                }
                String date = day + "/" + monthInAlpha + "/" + year;
                tripDate.setText(date);
            }
        };

        //TimePicker Dialog
        tripTime = view.findViewById(R.id.time);
        tripTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(cal.HOUR);
                int minute = cal.get(cal.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog,mTimeSetListener,hour,minute,true);
                timePickerDialog.show();
            }
        });
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                minute += 1;
                String time =hour +" : "+ minute;
                tripTime.setText(time);
            }
        };

        //Resize create post layour
        createPost = (RelativeLayout) view.findViewById(R.id.create_post);
        createPost.getLayoutParams().height = 400;

        //OnClick write a post
        writePost = view.findViewById(R.id.writePost);
        extendedPost = view.findViewById(R.id.extended_post);
        writePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost.setLayoutParams(new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); //Resize createpost layout on click
                writePost.setVisibility(view.INVISIBLE);         //making write post TextView invisible
                extendedPost.setVisibility(view.VISIBLE);        //making extended post layout visible
            }
        });

        //Making a spinner
        Spinner toCity = (Spinner) view.findViewById(R.id.to_city);                                    //ToCity Spinner
        ArrayAdapter<CharSequence> toCityAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        toCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toCity.setAdapter(toCityAdapter);

        Spinner fromCity = (Spinner) view.findViewById(R.id.from_city);                                 //FromCity Spinner
        ArrayAdapter<CharSequence> fromCityAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        fromCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCity.setAdapter(fromCityAdapter);

        //Submit Post
        submitPost = view.findViewById(R.id.submit_post);

        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        //Recycler View
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.posts_recycler);
        PostsListAdapter adapter = new PostsListAdapter(/*getContext(),mImages,mUsernames,mDurations,mFromCity,mToCity,mTripDate,mTripTime,mDiscription*/);
        recycler.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        return view;
    }

}
