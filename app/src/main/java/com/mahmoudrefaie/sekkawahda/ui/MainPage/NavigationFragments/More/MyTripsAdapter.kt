package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.More

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.Pojo.User
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile.MyProfile
import com.mahmoudrefaie.sekkawahda.ui.Profile.OtherProfiles.NotReservedUserProfile
import com.mahmoudrefaie.sekkawahda.ui.Profile.OtherProfiles.ReservedUserProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response

class MyTripsAdapter(private val context: Context, private val trips: List<Trip>) : RecyclerView.Adapter<MyTripsAdapter.MyTripHolder>() {

    //private var trips: List<Trip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mytrips_cardview, parent, false)
        return MyTripHolder(view)
    }

    override fun onBindViewHolder(holder: MyTripHolder, position: Int) {
        val trip = trips[position]
        holder.bindView(trip)
    }

    override fun getItemCount(): Int = trips.size

//    fun setList(trips : List<Trip>){
//        Log.d(TAG, "setList: called.")
//        this.trips = trips
//        notifyDataSetChanged()
//    }

    inner class MyTripHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var postProfPic: CircleImageView
        var driverId : Int = 0
        var tripId : Int = 0
        var username: TextView
        var fromCity: TextView
        var toCity: TextView
        var tripDate: TextView
        var tripTime: TextView
        var state: TextView
        var placeToMeet: TextView
        var postTime:TextView
        var postUnit:TextView
        var profileType : String? = null

        private var accessToken: String? = null
        private var appAuth: SharedPreferences? = null
        private var userId: Int? = 0

        init {
            postProfPic = itemView.findViewById(R.id.post_profPic)
            username = itemView.findViewById(R.id.usernameProf)
            fromCity = itemView.findViewById(R.id.from_city)
            toCity = itemView.findViewById(R.id.to_city)
            tripDate = itemView.findViewById(R.id.date)
            tripTime = itemView.findViewById(R.id.time)
            state =  itemView.findViewById(R.id.state_result)
            placeToMeet = itemView.findViewById(R.id.place_to_meet)
            postTime = itemView.findViewById(R.id.post_date_time)
            postUnit = itemView.findViewById(R.id.post_date_unit)

            appAuth = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            accessToken = appAuth?.getString("TOKEN", null)
            userId = appAuth?.getInt("userId", userId!!)

            postProfPic.setOnClickListener(this)
            username.setOnClickListener(this)
        }

        fun bindView(postItem: Trip) {
            driverId = postItem.driverId
            tripId = postItem.tripId
            username.text = postItem.driverName
            fromCity.text = postItem.fromCity
            toCity.text = postItem.toCity
            tripDate.text = postItem.tripDate
            tripTime.text = postItem.tripTime
            state.text = postItem.state
            placeToMeet.text = postItem.placeToMeet
            postTime.text = postItem.postTime.time.toString()
            postUnit.text = postItem.postTime.unit

            var imageName = postItem.driverImageUrl

            determineProfileType(driverId, accessToken!!)

            try {
                imageName = imageName.replace("~/", "")
                val imageUrl = "https://seka.azurewebsites.net/$imageName"
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.blank_profile_pic)
                        .into(postProfPic)
            }catch (e: Exception){
                imageName = R.drawable.blank_profile_pic.toString()
                Picasso.get().load(imageName).into(postProfPic)
            }
        }

        override fun onClick(v: View?) {
            if(v?.id == R.id.post_profPic || v?.id == R.id.usernameProf){
                updateToUserProfile()
            }
        }

        private fun updateToUserProfile(){
            if(profileType.equals("UserProfile")){
                val intent = Intent(context, MyProfile::class.java)
                context.startActivity(intent)
            }
            else if(profileType.equals("ProfileReserved")){
                val intent = Intent(context, ReservedUserProfile::class.java)
                val bundle = Bundle()
                bundle.putInt("profile_id", driverId)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
            else{       //if ProfileNotReserved
                val intent = Intent(context, NotReservedUserProfile::class.java)
                val bundle = Bundle()
                bundle.putInt("profile_id", driverId)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }

        private fun determineProfileType(profileId: Int, authToken: String){
            val call: Call<User>? = RetrofitClient.instance?.api?.getProfileDetails(profileId, "Bearer $authToken")
            call?.enqueue(object : retrofit2.Callback<User> {
                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Log.e("onFailure : ", t.message!!)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        profileType = response.body()?.profileType
                        //Log.e("OnResponse Details : ", "successful")
                    } else {
                        Log.e("OnResponse Details : ", "isn't successful")
                    }
                }
            })
        }

    }

    companion object {
        private const val TAG = "PostsListAdapter"
    }
}