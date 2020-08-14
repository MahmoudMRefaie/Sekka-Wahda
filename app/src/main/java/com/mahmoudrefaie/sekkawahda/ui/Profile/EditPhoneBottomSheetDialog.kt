package com.mahmoudrefaie.sekkawahda.ui.Profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
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

class EditPhoneBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private var bottomSheetListener : BottomSheetListener ?= null

    private lateinit var editPhone : TextInputLayout
    private lateinit var cancel : Button
    private lateinit var save : Button

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.phone_bottom_sheet_dialog,container,false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        editPhone = v.findViewById(R.id.phone)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        val phone : String? = this.arguments?.getString("phone")

        editPhone.editText?.setText(phone)
        editPhone.isFocusable = true
        editPhone.requestFocus()

        cancel.setOnClickListener(this)
        save.setOnClickListener (this)

        return v
    }
    override fun onClick(p0: View?) {
        if(view?.id == R.id.cancel_btn){
            dismiss()
        }
        else if(view?.id == R.id.save_btn){
            if(!validatePhoneNo())
                return

            val editedphone = editPhone?.editText?.text.toString()
            updatePhone(editedphone, accessToken!!)
            dismiss()
        }
    }

    interface BottomSheetListener{
        fun onEditPhoneButtonClicked(text : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e : ClassCastException){
            throw ClassCastException(context.toString() + " must implement BottomSheetListener")
        }
    }

    private fun updatePhone(newPhoneNumber: String, token: String) {
        val call : Call<String>? = RetrofitClient.instance?.api?.updateProfilePhone(newPhoneNumber, "Bearer $token")
        call?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(context, "Check your connection", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body().toString()
                        Log.e("onResponse Successfully", res)
                        bottomSheetListener?.onEditPhoneButtonClicked(newPhoneNumber)
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

    private fun validatePhoneNo(): Boolean {
        val valiPhone: String = editPhone.getEditText()?.getText().toString().trim()
        return if (valiPhone.isEmpty()) {
            editPhone.setError("SSN can't be empty")
            false
        } else if (!isValidPhone(valiPhone)) {
            editPhone.setError("Not valid phone number")
            false
        } else {
            editPhone.setError(null)
            true
        }
    }
    fun isValidPhone(phone: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(phone)) {
            false
        } else {
            Patterns.PHONE.matcher(phone).matches()
        }
    }

}