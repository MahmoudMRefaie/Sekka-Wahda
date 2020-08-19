package com.mahmoudrefaie.sekkawahda.ui.MainPage.Notifications

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.Pojo.User
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.PostsListAdapter
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationAdapter (private val context: Context, private val notifications: List<NotificationResponse>): RecyclerView.Adapter<NotificationAdapter.NotificationsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false)
        Log.d("onCreateViewHolder", "onCreateViewHolder: called.")
        return NotificationsHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        val notification = notifications[position]
        holder.bindView(notification)
    }

    override fun getItemCount(): Int = notifications.size

    inner class NotificationsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var notificationMessage: TextView?
        var notificationType : String? = null
        var raiserId: Int? = null
        var tripId: Int? = null

        private var accessToken: String? = null
        private var appAuth: SharedPreferences? = null
        private var userId: Int? = 0

        init {
            notificationMessage = itemView.findViewById(R.id.message)

            appAuth = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            accessToken = appAuth?.getString("TOKEN", null)
            userId = appAuth?.getInt("userId",userId!!)
        }

        fun bindView(notificationItem: NotificationResponse){
            notificationType = notificationItem.notificationType

            if(notificationType.equals("ReserveAccepredDriver")){
                raiserId = notificationItem.raiserId
                notificationMessage?.text = notificationItem.message
            }
            else if(notificationType.equals("RequestReserveTrip")){
                tripId = notificationItem.tripId
                notificationMessage?.text = notificationItem.message
            }
        }

    }

}