package com.example.sekkawa7da;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsHolder> {
    private static final String TAG = "PostsListAdapter";

    /*
    CircleImageView profPic;
    TextView username , duration , fromCity , toCity , tripDate , tripTime , discription;
    CardView postCardView;*/
    /*private ArrayList<Integer> mImages = new ArrayList<>();
    private ArrayList<String> mUsernames , mDurations , mFromCity , mToCity , mTripDate , mTripTime , mDiscription
            = new ArrayList<>();
    private Context mContext;

    public PostsListAdapter(Context mContext, ArrayList<Integer> mImages, ArrayList<String> mUsernames, ArrayList<String> mDurations,
                            ArrayList<String> mFromCity, ArrayList<String> mToCity, ArrayList<String> mTripDate,
                            ArrayList<String> mTripTime, ArrayList<String> mDiscription) {
        this.mImages = mImages;
        this.mUsernames = mUsernames;
        this.mDurations = mDurations;
        this.mFromCity = mFromCity;
        this.mToCity = mToCity;
        this.mTripDate = mTripDate;
        this.mTripTime = mTripTime;
        this.mDiscription = mDiscription;
        this.mContext = mContext;
    }*/

    @NonNull
    @Override
    public PostsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cardview,parent,false);
        return new PostsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        ((PostsHolder)holder).bindView(position);
        /*Glide.
                with(mContext).asBitmap().load(mImages.get(position)).into(holder.postProfPic);

        holder.username.setText(mUsernames.get(position));
        holder.duration.setText(mDurations.get(position));
        holder.fromCity.setText(mFromCity.get(position));
        holder.toCity.setText(mToCity.get(position));
        holder.tripDate.setText(mTripTime.get(position));
        holder.discription.setText(mDiscription.get(position));
        holder.username.setText(mUsernames.get(position));

        holder.postCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG , "onClick : Clicked on: "+mUsernames.get(position));
                Toast.makeText(mContext,mUsernames.get(position),Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return OurData.mUsernames.length;
    }

    public class PostsHolder extends RecyclerView.ViewHolder{
        CircleImageView postProfPic;
        TextView username , duration , fromCity , toCity , tripDate , tripTime , discription;
        CardView postCardView;
        public PostsHolder(@NonNull View itemView) {
            super(itemView);
            postProfPic = (CircleImageView)itemView.findViewById(R.id.post_profPic);
            username = (TextView)itemView.findViewById(R.id.usernameProf);
            duration = (TextView)itemView.findViewById(R.id.post_date);
            fromCity = (TextView)itemView.findViewById(R.id.from_city);
            toCity = (TextView)itemView.findViewById(R.id.to_city);
            tripDate = (TextView)itemView.findViewById(R.id.date);
            tripTime = (TextView)itemView.findViewById(R.id.time);
            discription = (TextView)itemView.findViewById(R.id.post_description);
            postCardView = (CardView)itemView.findViewById(R.id.post_card);

        }
        public void bindView (int position){
            postProfPic.setImageResource(OurData.mImages[position]);
            username.setText(OurData.mUsernames[position]);
            duration.setText(OurData.mDurations[position]);
            fromCity.setText(OurData.mFromCity[position]);
            toCity.setText(OurData.mToCity[position]);
            tripDate.setText(OurData.mTripTime[position]);
            discription.setText(OurData.mDiscription[position]);
        }
    }
}