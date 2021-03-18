package com.mahmoudrefaie.sekkawahda.ui.Profile.EditBottomSheetDialogs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class EditDriverLicenseBottomSheetDialog : BottomSheetDialogFragment() , View.OnClickListener {

    private var bottomSheetListener : BottomSheetListener?= null

    private lateinit var editDriverLicense : TextInputLayout
    private lateinit var cancel : Button
    private lateinit var save : Button

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.driverlicense_bottom_sheet_dialog,container,false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        editDriverLicense = v.findViewById(R.id.driver_license)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        val driverLicense : String? = this.arguments?.getString("driver_license")

        editDriverLicense.editText?.setText(driverLicense)
        editDriverLicense.isFocusable = true
        editDriverLicense.requestFocus()

        cancel.setOnClickListener(this)
        save.setOnClickListener (this)

        return v
    }

    interface BottomSheetListener{
        fun onEditDriverLicenseButtonClicked(text : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e : ClassCastException){
            throw ClassCastException("$context must implement BottomSheetListener")
        }
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.cancel_btn){
            dismiss()
        }
        else if(view?.id == R.id.save_btn){
            val editedDriverLicense = editDriverLicense?.editText?.text.toString().trim()
            updateDriverLicense(editedDriverLicense,accessToken!!)
            dismiss()
        }
    }

    private fun updateDriverLicense(newDriverLicense : String, token : String){
        val call : Call<String>? = RetrofitClient.instance?.api?.updateProfileDriverLicense(newDriverLicense, "Bearer $token")
        call?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(context, "Check your connection", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body().toString()
                        Log.e("onResponse Successfully", res)
                        bottomSheetListener?.onEditDriverLicenseButtonClicked(newDriverLicense)
                    } else {
                        val res = response.errorBody().toString()
                        Log.e("Error Body", res)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }
}