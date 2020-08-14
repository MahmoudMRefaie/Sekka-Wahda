package com.mahmoudrefaie.sekkawahda.ui.Profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class EditCityBottomSheetDialog : BottomSheetDialogFragment() , View.OnClickListener {

    private var bottomSheetListener : BottomSheetListener ?= null

    private lateinit var editCity : Spinner
    private lateinit var cancel : Button
    private lateinit var save : Button

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.city_bottom_sheet_dialog,container,false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        editCity = v.findViewById(R.id.spinner_dialog)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        //Cities Spinner
        val adapter = ArrayAdapter.createFromResource(activity!!,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        editCity.setAdapter(adapter)
        editCity.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                (parentView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!,R.color.white))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })

        cancel.setOnClickListener(this)
        save.setOnClickListener (this)

        return v
    }

    interface BottomSheetListener{
        fun onEditCityButtonClicked(text : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e : ClassCastException){
            throw ClassCastException(context.toString() + " must implement BottomSheetListener")
        }
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.cancel_btn){
            dismiss()
        }
        else if(view?.id == R.id.save_btn){
            if (!validateCity()) {
                return
            }
            val editedCity = editCity.selectedItem.toString()
            updateCity(editedCity,accessToken!!)
            dismiss()
        }
    }

    private fun updateCity(newCity : String, token : String){
        val call : Call<String>? = RetrofitClient.instance?.api?.updateProfileCity(newCity, "Bearer $token")
        call?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(context, "Check your connection", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body().toString()
                        Log.e("onResponse Successfully", res)
                        bottomSheetListener?.onEditCityButtonClicked(newCity) //If response is not successful city will not update
                    } else {
                        val res = response.errorBody().toString()
                        Log.e("Error Body", res) }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun validateCity(): Boolean {
        val valiCity: String = editCity.getSelectedItem().toString()
        return if (valiCity.contains(":")) {
            Toast.makeText(context,"Select your city",Toast.LENGTH_LONG).show()
            false
        } else {
            true
        }
    }
}