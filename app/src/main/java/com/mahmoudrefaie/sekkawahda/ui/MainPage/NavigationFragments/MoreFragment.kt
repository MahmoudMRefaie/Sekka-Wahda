package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.UserIdResponse
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Login.Login
import com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile.MyProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_more.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoreFragment : Fragment(), View.OnClickListener {


    lateinit var profilePic : CircleImageView
    lateinit var profileName : TextView
    lateinit var profile : RelativeLayout
    lateinit var logout : RelativeLayout

    private var sharedPre: SharedPreferences? = null
    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState);
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        sharedPre = activity?.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        getUserImageandName(accessToken!!)

        profilePic = view.findViewById(R.id.profile_pic)
        profileName = view.findViewById(R.id.profile_name)
        profile = view.findViewById(R.id.profile)
        logout = view.findViewById(R.id.logout)

        profile.setOnClickListener(this)
        logout.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.profile){
            val intent = Intent(activity, MyProfile::class.java)
            startActivity(intent)
        }
        else if(v?.id == R.id.logout){
            Toast.makeText(activity,"Logout clicked",Toast.LENGTH_LONG).show()
            val editor: SharedPreferences.Editor = sharedPre?.edit()!!
            Log.e("Log Out", sharedPre?.getBoolean("logged", true).toString())
            val editorAuth: SharedPreferences.Editor = appAuth?.edit()!!
            editor.remove("logged")
            editorAuth.remove("TOKEN")
            editor.commit()
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun getUserImageandName(authToken: String) {
        val call : Call<UserIdResponse>? = RetrofitClient.instance?.api?.getUserInfoToAccessProfile("Bearer $authToken")
        call?.enqueue(object : Callback<UserIdResponse> {
            override fun onFailure(call: Call<UserIdResponse?>, t: Throwable) {
                //Toast.makeText(this@Profile, "Check your connection", Toast.LENGTH_LONG).show()
                Toast.makeText(activity, "OnFailure : " + t.message, Toast.LENGTH_LONG).show()
                Log.e("onFailure : ", t.message)
            }

            override fun onResponse(call: Call<UserIdResponse>, response: Response<UserIdResponse>) {
                if (response.isSuccessful) {
                    val userId = response.body()?.userId
                    val profileUserName = response.body()?.name
                    var imageName = response.body()?.profileImageUrl

                    val editor: SharedPreferences.Editor = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)!!.edit()
                    editor.putInt("userId", userId!!)
                    editor.apply()

                    profileName.text = profileUserName

                    try {
                        imageName = imageName?.replace("~/", "")
                        val imageUrl = "https://seka.azurewebsites.net/$imageName"
                        Log.e("Image URL ", imageUrl)
                        Picasso.get()
                                .load(imageUrl)
                                .placeholder(R.drawable.blank_profile_pic)
                                .into(profilePic)
                    }catch (e : Exception){
                        imageName = R.drawable.blank_profile_pic.toString()
                        Picasso.get()
                                .load(imageName)
                                .placeholder(R.drawable.blank_profile_pic)
                                .into(profilePic)
                    }

                } else {
                    Log.e("OnResponse : ", "isn't successful")
                }
            }
        })
    }
}