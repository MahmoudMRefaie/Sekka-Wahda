package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.Pojo.User
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.PostsListAdapter.PostsHolder
import com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile.MyProfile
import com.mahmoudrefaie.sekkawahda.ui.Profile.NotReservedUserProfile
import com.mahmoudrefaie.sekkawahda.ui.Profile.ReservedUserProfile
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response

class PostsListAdapter(private val context: Context, private val trips: List<Trip>) : RecyclerView.Adapter<PostsHolder>() {

    //private var trips: List<Trip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_cardview, parent, false)
        Log.d(TAG, "onCreateViewHolder: called.")
        return PostsHolder(view)
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")
        val trip = trips[position]
        holder.bindView(trip)
    }

    override fun getItemCount(): Int = trips.size

//    fun setList(trips : List<Trip>){
//        Log.d(TAG, "setList: called.")
//        this.trips = trips
//        notifyDataSetChanged()
//    }

    inner class PostsHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var postProfPic: CircleImageView
        var driverId : Int = 0
        var tripId : Int = 0
        var username: TextView
        var fromCity: TextView
        var toCity: TextView
        var tripDate: TextView
        var tripTime: TextView
        var placeToMeet: TextView
        var postTime:TextView
        var postUnit:TextView
        var reserveBtn: Button
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
            placeToMeet = itemView.findViewById(R.id.place_to_meet)
            postTime = itemView.findViewById(R.id.post_date_time)
            postUnit = itemView.findViewById(R.id.post_date_unit)
            reserveBtn = itemView.findViewById(R.id.reserve)

            appAuth = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            accessToken = appAuth?.getString("TOKEN", null)
            userId = appAuth?.getInt("userId",userId!!)

            reserveBtn.setOnClickListener(this)
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
            if (v?.id == R.id.reserve){
                openConfirmReservationDialog()
            }
            else if(v?.id == R.id.post_profPic || v?.id == R.id.usernameProf){
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
                bundle.putInt("profile_id",driverId)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
            else{       //if ProfileNotReserved
                val intent = Intent(context, NotReservedUserProfile::class.java)
                val bundle = Bundle()
                bundle.putInt("profile_id",driverId)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }

        private fun determineProfileType(profileId : Int, authToken : String){
            val call: Call<User>? = RetrofitClient.instance?.api?.getProfileDetails(profileId,"Bearer $authToken")
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

        private fun openConfirmReservationDialog(){
            val confirmReservationDialog = ConfirmReservationDialog(tripId, accessToken!!, username.text.toString())
            val sfm = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            confirmReservationDialog.show(sfm,"Confirm Reservation Dialog")
        }



    }

    companion object {
        private const val TAG = "PostsListAdapter"
    }
}