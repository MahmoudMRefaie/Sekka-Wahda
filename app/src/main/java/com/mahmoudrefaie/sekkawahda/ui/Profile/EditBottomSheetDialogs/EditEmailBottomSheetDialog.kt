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
import java.util.regex.Pattern

class EditEmailBottomSheetDialog : BottomSheetDialogFragment() {

    private var bottomSheetListener : BottomSheetListener?= null

    private lateinit var editEmail : TextInputLayout
    private lateinit var cancel : Button
    private lateinit var save : Button


    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.email_bottom_sheet_dialog,container,false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        editEmail = v.findViewById(R.id.email)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        val email : String? = this.arguments?.getString("email")

        editEmail.editText?.setText(email)
        editEmail.isFocusable = true
        editEmail.requestFocus()

        cancel.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        save.setOnClickListener {
            if (!validateEmail()) {
                  return@setOnClickListener
            }
            val editedEmail = editEmail.editText?.text.toString().trim()
            updateEmail(editedEmail,accessToken!!)
            dismiss()
        }
        return v
    }

    interface BottomSheetListener{
        fun onEditEmailButtonClicked(text : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e : ClassCastException){
            throw ClassCastException("$context must implement BottomSheetListener")
        }
    }

    private fun updateEmail(newEmail: String, token: String) {
        val call : Call<String>? = RetrofitClient.instance?.api?.updateProfileEmail(newEmail, "Bearer $token")
        call?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(context, "Check your connection", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body().toString()
                        Log.e("onResponse Successfully", res)
                        bottomSheetListener?.onEditEmailButtonClicked(newEmail)
                    } else {
                        val res = response.errorBody().toString()
                        Log.e("Error Body", res) }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun validateEmail(): Boolean {
        val valiEmail = editEmail.editText?.text.toString().trim()
        return if (valiEmail.isEmpty()) {
            editEmail.error = "Email can't be empty"
            false
        } else if (!isValidEmailAddress(valiEmail)) {
            editEmail.error = "Not valid email"
            false
        } else {
            editEmail.error = null
            true
        }
    }
    private fun isValidEmailAddress(email: String?): Boolean {
        val emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = Pattern.compile(emailPattern)
        val m = p.matcher(email)
        return m.matches()
    }
}