package com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileViewModel : ViewModel() {
    val profilePic = MutableLiveData<String>()
    val fullName = MutableLiveData<String>()
    val driverTotalRate = MutableLiveData<Float>()
    val city = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val ssn = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val carImageUrl = MutableLiveData<String>()
    val driverLicense = MutableLiveData<String>()
    val carLicense = MutableLiveData<String>()
    val carModel = MutableLiveData<String>()

    //to get profile details from Retrofit
    fun getProfileData (userId: Int , authToken : String) {
        getProfilePic(authToken)
        val call: Call<User>? = RetrofitClient.instance?.api?.getProfileDetails(userId,"Bearer $authToken")
        call?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e("onFailure : ", t.message!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {

                    fullName.value = response.body()?.fullName
                    driverTotalRate.value = response.body()?.driverTotalRate
                    city.value = response.body()?.city
                    email.value = response.body()?.userEmailID
                    phoneNumber.value = response.body()?.phoneNumber
                    ssn.value = response.body()?.ssn
                    carImageUrl.value = response.body()?.carImageUrl
                    if(driverLicense.value.equals("")  || driverLicense.value == null){
                        driverLicense.value = response.body()?.driverLicense
                    }else{
                        Log.e("Driver License ","You can edit driver license one time")
                    }
                    carLicense.value = response.body()?.carLicense
                    carModel.value = response.body()?.carModel
                } else {
                    Log.e("OnResponse Details : ", "isn't successful")
                }
            }
        })
    }

    //to get profile picture from Retrofit
    fun getProfilePic(authToken : String){  //making it alone in a function because it alone in a request
        val call : Call<String>? = RetrofitClient.instance?.api?.getProfilePic("Bearer $authToken")
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("onFailure : ",t.message)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    profilePic.value = response.body()
                    Log.e("OnResponse Pic : ", response.body())
                }else{
                    Log.e("OnResponse Pic : ", "isn't successful")
                }
            }
        })
    }

}