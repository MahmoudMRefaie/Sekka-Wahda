package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.Pojo.User
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val fromCity = MutableLiveData<String>()
    val toCity = MutableLiveData<String>()
    val tripTime = MutableLiveData<Float>()
    val tripDate = MutableLiveData<String>()
    val placeToMeet = MutableLiveData<String>()

    val postsMutableLiveData = MutableLiveData<List<Trip>>()


    fun getTrips(authToken: String){
        val call: Call<List<Trip>>? = RetrofitClient.instance?.api?.getTrips("Bearer $authToken")
        call?.enqueue(object: Callback<List<Trip>> {
            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                Log.e("onFailure loading posts",t.message!!)
                //Toast.makeText(activity,"Check your connection",Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                if(response.isSuccessful){
                    var tripData : List<Trip> = response.body()!!
                    Log.e("OnResponse : ", tripData.toString())
                    //Toast.makeText(activity,tripData.toString(),Toast.LENGTH_LONG).show()
                    postsMutableLiveData.value = tripData
                }else{
                    Log.e("OnResponse : ",response.body().toString())
                    //Toast.makeText(activity,response.body().toString(),Toast.LENGTH_LONG).show()
                    //Toast.makeText(activity,response.code().toString(),Toast.LENGTH_LONG).show()
                    //updateUItoProfile()
                }
            }
        })
    }//
}