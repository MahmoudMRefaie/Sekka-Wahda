package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Notifications

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.sekkawahda.Pojo.NotificationResponse
import com.mahmoudrefaie.sekkawahda.R

class NotificationAdapter (private val context: Context, private val notifications: List<NotificationResponse>): RecyclerView.Adapter<NotificationAdapter.NotificationsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false)
        Log.d("NotificationAdapter", "onCreateViewHolder: called.")
        return NotificationsHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        val notification = notifications[position]
        holder.bindView(notification)
        holder.itemView.setOnClickListener({
            Toast.makeText(context,"View Clicked",Toast.LENGTH_LONG).show()
        })
    }

    override fun getItemCount(): Int = notifications.size

    inner class NotificationsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var notificationMessage: TextView?
        var notificationType : String? = null
        var raiserId: Int? = null
        var tripId: Int? = null

        private var accessToken: String? = null
        private var appAuth: SharedPreferences? = null

        init {
            notificationMessage = itemView.findViewById(R.id.message)

            appAuth = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            accessToken = appAuth?.getString("TOKEN", null)
        }

        fun bindView(notificationItem: NotificationResponse){
            notificationType = notificationItem.notificationType

            if(notificationType.equals("ReserveAccepredDriver")){
                raiserId = notificationItem.raiserId
                notificationMessage?.text = notificationItem.message
                Log.e("raiserID : ",raiserId.toString())
            }
            else if(notificationType.equals("RequestReserveTrip")){
                tripId = notificationItem.tripId
                notificationMessage?.text = notificationItem.message
            }
            else if(notificationType.equals("ReserveNotAccepted")){
                notificationMessage?.text = notificationItem.message
            }
            else if(notificationType.equals("reserveAcceptedTraveller")){
                tripId = notificationItem.tripId
                notificationMessage?.text = notificationItem.message
            }
        }

    }

}