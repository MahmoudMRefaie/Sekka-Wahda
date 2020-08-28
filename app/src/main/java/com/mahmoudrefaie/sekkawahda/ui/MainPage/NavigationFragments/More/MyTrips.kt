package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.More

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.PostsListAdapter
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications.NotificationAdapter
import kotlinx.android.synthetic.main.activity_my_trips.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTrips : AppCompatActivity() {

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_trips)

        appAuth = getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        swipe_refresh.setColorSchemeResources(R.color.proj_sub_color)
        swipe_refresh.setOnRefreshListener {
            getMyTrips(accessToken!!)
        }
        getMyTrips(accessToken!!)

    }

    private fun getMyTrips(authToken: String) {
        swipe_refresh.isRefreshing = true
        val call: Call<List<Trip>>? = RetrofitClient.instance?.api?.myTrips("Bearer $authToken")
        call?.enqueue(object : Callback<List<Trip>> {
            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                swipe_refresh?.isRefreshing = false
                Log.e("onFailure loading posts", t.message)
                Toast.makeText(applicationContext, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                if (response.isSuccessful) {
                    swipe_refresh?.isRefreshing = false
                    var tripData: List<Trip> = response.body()!!
                    Log.e("OnResponse : ", tripData.toString())
                    //Toast.makeText(activity,tripData.toString(),Toast.LENGTH_LONG).show()
                    showMyTrips(applicationContext, tripData)
                } else {
                    swipe_refresh?.isRefreshing = false
                    Log.e("OnResponse : ", response.body().toString())
                    Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showMyTrips(context: Context, tripData: List<Trip>) {
        mytrips_recycler!!.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = MyTripsAdapter(context, tripData!!)
        }
    }
}