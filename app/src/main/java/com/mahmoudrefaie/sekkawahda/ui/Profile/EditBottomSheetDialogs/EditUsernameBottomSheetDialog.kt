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

class EditUsernameBottomSheetDialog : BottomSheetDialogFragment() , View.OnClickListener {

    private var bottomSheetListener : BottomSheetListener?= null

    private lateinit var editUsername : TextInputLayout
    private lateinit var cancel : Button
    private lateinit var save : Button

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.username_bottom_sheet_dialog,container,false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        editUsername = v.findViewById(R.id.username)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        val username : String? = this.arguments?.getString("username")

        editUsername.editText?.setText(username)
        editUsername.isFocusable = true
        editUsername.requestFocus()

        cancel?.setOnClickListener(this)
        save.setOnClickListener (this)

        return v
    }

    interface BottomSheetListener{
        fun onEditUsernameButtonClicked(text : String)
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
            if(!validateUsername()){
                return
            }
            val editedUsername = editUsername?.editText?.text.toString()
            updateUsername(editedUsername,accessToken!!)
            dismiss()
        }
    }

    private fun updateUsername(newUsername : String, token : String){
        val call : Call<String>? = RetrofitClient.instance?.api?.updateProfileFullName(newUsername, "Bearer $token")
        call?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(context, "Check your connection", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body().toString()
                        Log.e("onResponse Successfully", res)
                        bottomSheetListener?.onEditUsernameButtonClicked(newUsername)
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

    private fun validateUsername(): Boolean {
        val valiUsername: String = editUsername.getEditText()?.getText().toString()
        return if (valiUsername.isEmpty()) {
            editUsername.setError("Username can't be empty")
            false
        } else if (valiUsername.length > 22) {
            editUsername.setError("Username is too long")
            false
        } else {
            editUsername.setError(null)
            true
        }
    }
}