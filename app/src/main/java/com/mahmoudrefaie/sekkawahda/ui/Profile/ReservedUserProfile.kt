package com.mahmoudrefaie.sekkawahda.ui.Profile

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.User
import com.mahmoudrefaie.sekkawahda.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservedUserProfile : AppCompatActivity() {

    private var accessToken: String? = null
    private var appAuth: SharedPreferences? = null
    private var userId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserved_user_profile)

        appAuth = getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)
        userId = appAuth?.getInt("userId",userId!!)

        val profileId = intent.getIntExtra("profile_id",0)

        toolbar.setTitle("")
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.proj_main_color))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)          //BackArraw at ToolBar
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_blue_24dp)

        getUserProfileData(profileId,accessToken!!)

    }

    fun getUserProfileData (profileId: Int , authToken : String) {
        val call: Call<User>? = RetrofitClient.instance?.api?.getProfileDetails(profileId,"Bearer $authToken")
        call?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e("onFailure : ", t.message!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    username.text = response.body()?.fullName
                    ratingBar.rating = response.body()?.driverTotalRate!!
                    city_result.text = response.body()?.city
                    email_result.text = response.body()?.userEmailID
                    phone_result.text = response.body()?.phoneNumber
                    ssn_result.text = response.body()?.ssn
                    driver_license_result.text = response.body()?.driverLicense
                    car_license_result.text = response.body()?.carLicense
                    car_model_result.text = response.body()?.carModel

                    var carImageName = response.body()?.carImageUrl
                    try {
                        carImageName = carImageName?.replace("~/", "")
                        val imageUrl = "https://seka.azurewebsites.net/$carImageName"
                        Picasso.get()
                                .load(imageUrl)
                                .into(car_image_img)
                    }catch (e: Exception){
                        carImageName = R.drawable.blank_profile_pic.toString()
                        Picasso.get().load(carImageName).into(car_image_img)
                    }

                    var profilePicName = response.body()?.profilePicImage
                    try {
                        profilePicName = profilePicName?.replace("~/", "")
                        val imageUrl = "https://seka.azurewebsites.net/$profilePicName"
                        Picasso.get()
                                .load(imageUrl)
                                .into(show_image)
                    }catch (e: Exception){
                        profilePicName = R.drawable.blank_profile_pic.toString()
                        Picasso.get().load(profilePicName).into(show_image)
                    }
                    profile_progress_bar.visibility = View.INVISIBLE
                } else {
                    Log.e("OnResponse Details : ", "isn't successful")
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}