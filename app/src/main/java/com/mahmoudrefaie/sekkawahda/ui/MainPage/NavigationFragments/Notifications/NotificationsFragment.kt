package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse
import com.mahmoudrefaie.sekkawahda.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    private var refreshLayout: SwipeRefreshLayout? = null

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null


    lateinit var recycler: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState);
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        recycler = view.findViewById(R.id.notifications)

        refreshLayout = view.findViewById(R.id.refreshLayout)
        refreshLayout?.setOnRefreshListener {
            getNotification(accessToken!!)
        }
        getNotification(accessToken!!)

        return view
    }

    fun getNotification(authToken: String){
        refreshLayout?.isRefreshing = true
        val call: Call<List<NotificationResponse>>? = RetrofitClient.instance?.api?.notifications("Bearer $authToken")
        call?.enqueue(object : Callback<List<NotificationResponse>> {
            override fun onFailure(call: Call<List<NotificationResponse>>, t: Throwable) {
                refreshLayout?.isRefreshing = false
                Log.e("onFailure loading posts", t.message)
                Toast.makeText(activity, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<NotificationResponse>>, response: Response<List<NotificationResponse>>) {
                if (response.isSuccessful) {
                    refreshLayout?.isRefreshing = false
                    var notificationData: List<NotificationResponse> = response.body()!!
                    Log.e("OnResponse Notificaty: ", notificationData.toString())
                    //Toast.makeText(activity,tripData.toString(),Toast.LENGTH_LONG).show()
                    showNotification(activity!!, notificationData)
                    //recyclerProgressBar.visibility = View.INVISIBLE
                } else {
                    refreshLayout?.isRefreshing = false
                    Log.e("OnResponse : ", response.body().toString())
                    Toast.makeText(activity, response.body().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(activity, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showNotification(context: Context, notificationData: List<NotificationResponse>) {
        recycler!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NotificationAdapter(context, notificationData!!)
        }
    }
}