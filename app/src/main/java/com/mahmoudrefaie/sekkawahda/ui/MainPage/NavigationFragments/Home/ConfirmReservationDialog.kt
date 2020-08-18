package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.R
import retrofit2.Call
import retrofit2.Response

class ConfirmReservationDialog(private val tripId: Int, private val authToken: String, private val driverName: String): AppCompatDialogFragment() {

    lateinit var message: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.confirm_reservation_dialog,null)

        message = view!!.findViewById(R.id.message)
        message.text = "Are you sure you want to reserve $driverName trip ?"

        Log.e("authToken: ",authToken)

        builder.setView(view)
                .setTitle("Confirm Reservation")
                .setNegativeButton("cancel", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                    }
                })
                .setPositiveButton("confirm",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        makeReservation(tripId,authToken)
                    }
                })
        return builder.create()
    }

    private fun makeReservation(tripId : Int, authToken : String){
        Log.e("tripId: ",tripId.toString())
        val call: Call<String>? = RetrofitClient.instance?.api?.reserveTrip(tripId.toString(),"Bearer $authToken")
        call?.enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(context,"Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    //Toast.makeText(context,response.body(), Toast.LENGTH_LONG).show()
                    Log.e("OnResponse : ", "Trip reserved")
                } else {
                    Log.e("isn't successful : ", "this trip already reserved")
                }
            }
        })
    }
}