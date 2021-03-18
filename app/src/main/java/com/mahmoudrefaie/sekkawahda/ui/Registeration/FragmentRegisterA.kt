package com.mahmoudrefaie.sekkawahda.ui.Registeration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputLayout
import com.mahmoudrefaie.sekkawahda.R
import java.util.regex.Pattern


class FragmentRegisterA : Fragment(), View.OnClickListener {
    private var username: TextInputLayout? = null
    private var email: TextInputLayout? = null
    private var password: TextInputLayout? = null
    private var next: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register_a, container, false)
        username = view.findViewById(R.id.user)
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.pass)
        next = view.findViewById(R.id.next)
        next?.setOnClickListener(this)

        return view
    }

    override fun onClick(view: View) {
        if (view.id == R.id.next) {
            if (!validateUsername() or !validatePassword() or !validateEmail()) {
                return
            }
            val getUserName = username!!.editText!!.text.toString().trim()
            val getEmail = email!!.editText!!.text.toString().trim()
            val getPassword = password!!.editText!!.text.toString().trim()

            //Using Bundle  to send username, email, password data to another fragment
            val bundle = Bundle()
            bundle.putString("username", getUserName)
            bundle.putString("email", getEmail)
            bundle.putString("password", getPassword)

            //To open second fragment
            val manager = activity!!.supportFragmentManager
            val secondFragment = FragmentRegisterB()
            secondFragment.arguments = bundle //send data to another fragment
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.from_secondfrag_to_firstfrag, R.anim.exit_secondfrag_to_firstfrag,
                            R.anim.from_firstfrag_to_secondfrag, R.anim.exit_firstfrag_to_secondfrag)
                    .replace(R.id.fragment_register_layout, secondFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
        }
    }

    private fun validateUsername(): Boolean {
        val valiUsername = username!!.editText!!.text.toString().trim { it <= ' ' }
        return if (valiUsername.isEmpty()) {
            username!!.error = "Username can't be empty"
            false
        } else if (valiUsername.length > 20) {
            username!!.error = "Username is too long"
            false
        } else {
            username!!.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val valiEmail = email!!.editText!!.text.toString().trim { it <= ' ' }
        return if (valiEmail.isEmpty()) {
            email!!.error = "Email can't be empty"
            false
        } else if (!isValidEmailAddress(valiEmail)) {
            email!!.error = "Not valid email"
            false
        } else {
            email!!.error = null
            true
        }
    }

    private fun isValidEmailAddress(email: String?): Boolean {
        val emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = Pattern.compile(emailPattern)
        val m = p.matcher(email)
        return m.matches()
    }

    private fun validatePassword(): Boolean {
        val valiPasword = password!!.editText!!.text.toString().trim()
        return if (valiPasword.isEmpty()) {
            password!!.error = "Password can't be empty"
            false
        }
        else if(valiPasword.contains(" ")){
            password!!.error = "Password can't be contain spaces"
            false
        }
        else {
            password!!.error = null
            true
        }
    }
}