package com.mahmoudrefaie.sekkawahda.ui.Registeration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputLayout
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient.Companion.instance
import com.mahmoudrefaie.sekkawahda.Pojo.LoginResponse
import com.mahmoudrefaie.sekkawahda.Pojo.User
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.SharedPreferences.SharedPrefManager
import com.mahmoudrefaie.sekkawahda.ui.Login.Login
import com.mahmoudrefaie.sekkawahda.ui.MainPage.MainPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class FragmentRegisterB : Fragment(), View.OnClickListener {
    private var ssn: TextInputLayout? = null
    private var phone_no: TextInputLayout? = null
    private var city: Spinner? = null
    private var isAgree: CheckBox? = null
    private var previous: TextView? = null
    private var registerBtn: Button? = null
    private var loginResponse: LoginResponse? = null
    private var sharedPre: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_b, container, false)
        ssn = view.findViewById(R.id.ssn)
        phone_no = view.findViewById(R.id.phone)
        city = view.findViewById(R.id.city)
        isAgree = view.findViewById(R.id.agreeCheck)
        previous = view.findViewById(R.id.previous)
        previous?.setOnClickListener(this)
        registerBtn = view.findViewById(R.id.regBtn)
        registerBtn?.setOnClickListener(this)
        sharedPre = activity!!.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        //City Spinner
        val adapter = ArrayAdapter.createFromResource(activity!!,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        city?.setAdapter(adapter)

        //Check if poivacy checkbox is chcked
        isAgree?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                registerBtn?.setEnabled(true)
                registerBtn?.setBackgroundColor(resources.getColor(R.color.proj_sub_color))
            } else {
                registerBtn?.setEnabled(false)
                registerBtn?.setBackgroundColor(resources.getColor(R.color.unclickable_Button))
            }
        })
        return view
    }

    override fun onClick(view: View) {
        if (view.id == R.id.previous) {

            //To open second fragment
            val manager = activity!!.supportFragmentManager
            val firstFragment = FragmentRegisterA()
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.from_firstfrag_to_secondfrag, R.anim.exit_firstfrag_to_secondfrag,
                            R.anim.from_secondfrag_to_firstfrag, R.anim.exit_secondfrag_to_firstfrag)
                    .replace(R.id.fragment_register_layout, firstFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
        } else if (view.id == R.id.regBtn) {
            if (!validateSSN() or !validatePhoneNo() or !validateCity()) {
                return
            }
            val getSsn = ssn!!.editText!!.text.toString().trim { it <= ' ' }
            val getPhone = phone_no!!.editText!!.text.toString().trim { it <= ' ' }
            val getCity = city!!.selectedItem.toString()

            //Using Bundle to receive username, email, password data from another fragment
            val getUserName = arguments!!.getString("username")
            val getEmail = arguments!!.getString("email")
            val getPassword = arguments!!.getString("password")
            makeRegister(getUserName, getEmail, getPassword, getSsn, getPhone, getCity)
        }
    }

    private fun makeRegister(username: String?, email: String?, pass: String?, ssn: String, phone: String, city: String) {
        val user = User(username, email, pass, ssn, phone, city)
        val call = instance!!.api.createUser(user)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body()
                        Toast.makeText(activity, "Registered Successfully", Toast.LENGTH_LONG).show()
                        makeLogin(username, pass)
                    } else {
                        val res = response.errorBody()!!.string()
                        Toast.makeText(activity, "Not Registered", Toast.LENGTH_LONG).show()
                        Log.e("Error Code", response.code().toString())
                        Log.e("Error Body", res)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(activity, "Check Your Internet Connection", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun makeLogin(user: String?, pass: String?) {
        val type = "password"
        val call = instance!!.api
                .getUserLogin(user, pass, type)

        //Toast.makeText(Login.this, username, Toast.LENGTH_LONG).show();
        call.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                loginResponse = response.body()
                try {
                    if (response.isSuccessful) {
                        SharedPrefManager.getInstance(activity).saveUser(loginResponse)
                        openHomeActivity()
                        sharedPre!!.edit().putBoolean("logged", true).apply()
                    } else {
                        val res = response.errorBody()!!.string()
                        Toast.makeText(activity, "Incorrect username or password", Toast.LENGTH_LONG).show()
                        //Log.e("Error Code", String.valueOf(response.code()));
                        //Log.e("Error Body", response.errorBody().toString());
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                Toast.makeText(activity, "Internet isn't connect", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun openHomeActivity() {
        val intent = Intent(activity, MainPage::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent)
        activity!!.finish()
    }

    private fun validateSSN(): Boolean {
        val valiSSN = ssn!!.editText!!.text.toString().trim { it <= ' ' }
        return if (valiSSN.isEmpty()) {
            ssn!!.error = "SSN can't be empty"
            false
        } else if (valiSSN.length != 14) {
            ssn!!.error = "SSN must be 14 digits"
            false
        } else {
            ssn!!.error = null
            true
        }
    }

    private fun validatePhoneNo(): Boolean {
        val valiPhone = phone_no!!.editText!!.text.toString().trim { it <= ' ' }
        return if (valiPhone.isEmpty()) {
            phone_no!!.error = "SSN can't be empty"
            false
        } else if (!isValidPhone(valiPhone)) {
            phone_no!!.error = "Not valid phone number"
            false
        } else {
            phone_no!!.error = null
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

    private fun validateCity(): Boolean {
        val valiCity = city!!.selectedItem.toString()
        return if (valiCity.isEmpty()) {
            false
        } else if (valiCity.contains(":")) {
            false
        } else {
            true
        }
    }

    private fun openLoginPage() {
        val intent = Intent(activity, Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity!!.finish()
    }
}