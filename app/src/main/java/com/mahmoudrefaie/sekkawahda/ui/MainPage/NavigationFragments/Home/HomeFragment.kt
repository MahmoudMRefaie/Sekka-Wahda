package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.UserIdResponse
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Login.Login
import com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {
    private var userProfilePic: CircleImageView? = null
    private var username: TextView? = null
    private var tripDate: TextView? = null
    private var mDateSetListener: OnDateSetListener? = null
    private var tripTime: TextView? = null
    private var mTimeSetListener: OnTimeSetListener? = null
    private var writePost: TextView? = null
    private var extendedPost: RelativeLayout? = null
    private var createPost: RelativeLayout? = null
    private var submitPost: Button? = null
    private val toCity: Spinner? = null
    private val fromCity: Spinner? = null

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    private var sharedPre: SharedPreferences? = null

    lateinit var logOut: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState);
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        sharedPre = activity?.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        //Load user image and name , store userId in SharedPreferences
        getUserImageandName(accessToken!!)

        userProfilePic = view.findViewById(R.id.profPicUser)
        username = view.findViewById(R.id.username)
        userProfilePic?.setOnClickListener(View.OnClickListener {
            updateUItoProfile()
        })
        username?.setOnClickListener(View.OnClickListener {
            updateUItoProfile()
        })

        logOut = view.findViewById(R.id.logout)
        logOut.setOnClickListener(View.OnClickListener {
            val editor: SharedPreferences.Editor = sharedPre?.edit()!!
            Log.e("Log Out" , sharedPre?.getBoolean("logged",true).toString())
            editor.remove("logged")
            editor.commit()
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
            activity?.finish()
        })

        //DatePicker Dialog
        tripDate = view.findViewById(R.id.date)
        tripDate?.setOnClickListener(View.OnClickListener {
            setTripDate()
        })
        mDateSetListener = OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month += 1
            var monthInAlpha = ""
            when (month) {
                1 -> monthInAlpha = "Jan"
                2 -> monthInAlpha = "Feb"
                3 -> monthInAlpha = "Mar"
                4 -> monthInAlpha = "Abr"
                5 -> monthInAlpha = "May"
                6 -> monthInAlpha = "Jun"
                7 -> monthInAlpha = "Jul"
                8 -> monthInAlpha = "Aug"
                9 -> monthInAlpha = "Sep"
                10 -> monthInAlpha = "Oct"
                11 -> monthInAlpha = "Nov"
                12 -> monthInAlpha = "Dec"
            }
            val date = "$day/$monthInAlpha/$year"
            tripDate?.setText(date)
        }

        //TimePicker Dialog
        tripTime = view.findViewById(R.id.time)
        tripTime?.setOnClickListener(View.OnClickListener {
            val cal = Calendar.getInstance()
            val hour = cal[Calendar.HOUR]
            val minute = cal[Calendar.MINUTE]
            val timePickerDialog = TimePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog, mTimeSetListener, hour, minute, true)
            timePickerDialog.show()
        })
        mTimeSetListener = OnTimeSetListener { timePicker, hour, minute ->
            var minute = minute
            minute += 1
            val time = "$hour : $minute"
            tripTime?.setText(time)
        }

        //Resize create post layour
        createPost = view.findViewById<View>(R.id.create_post) as RelativeLayout
        createPost!!.layoutParams.height = 400

        //OnClick write a post
        writePost = view.findViewById(R.id.writePost)
        extendedPost = view.findViewById(R.id.extended_post)
        writePost?.setOnClickListener(View.OnClickListener {
            createPost!!.layoutParams = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) //Resize createpost layout on click
            writePost?.setVisibility(View.INVISIBLE) //making write post TextView invisible
            extendedPost?.setVisibility(View.VISIBLE) //making extended post layout visible
        })

        //Making a spinner
        val toCity = view.findViewById<View>(R.id.to_city) as Spinner //ToCity Spinner
        val toCityAdapter = ArrayAdapter.createFromResource(context!!,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        toCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        toCity.adapter = toCityAdapter
        val fromCity = view.findViewById<View>(R.id.from_city) as Spinner //FromCity Spinner
        val fromCityAdapter = ArrayAdapter.createFromResource(context!!,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        fromCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromCity.adapter = fromCityAdapter

        //Submit Post
        submitPost = view.findViewById(R.id.submit_post)
        submitPost?.setOnClickListener(View.OnClickListener { })

        //Recycler View
        val recycler = view.findViewById<View>(R.id.posts_recycler) as RecyclerView
        val adapter = PostsListAdapter()
        recycler.adapter = adapter
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recycler.layoutManager = layoutManager
        return view
    }

    //Getting user id , profile picture , username
    private fun getUserImageandName (authToken : String) {
        val call : Call<UserIdResponse>? = RetrofitClient.instance?.api?.getUserInfoToAccessProfile("Bearer $authToken")
        call?.enqueue(object : Callback<UserIdResponse> {
            override fun onFailure(call: Call<UserIdResponse?>, t: Throwable) {
                //Toast.makeText(this@Profile, "Check your connection", Toast.LENGTH_LONG).show()
                Toast.makeText(activity,"OnFailure : "+t.message,Toast.LENGTH_LONG).show()
                Log.e("onFailure : ",t.message)
            }
            override fun onResponse(call: Call<UserIdResponse>, response: Response<UserIdResponse>) {
                if(response.isSuccessful){
                    val userId = response.body()?.userId
                    val profileUserName = response.body()?.name
                    var imageName = response.body()?.profileImageUrl

                    val editor: SharedPreferences.Editor = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)!!.edit()
                    editor.putInt("userId", userId!!)
                    editor.apply()

                    username?.text = profileUserName

                    imageName = imageName?.replace("~/","")
                    val imageUrl = "https://seka.azurewebsites.net/$imageName"

                    Log.e("UserId ",userId.toString())
                    Log.e("profileUserName ",profileUserName)
                    Log.e("imageUrl ",imageUrl)
                    Toast.makeText(activity,userId.toString(),Toast.LENGTH_LONG).show()
                    Toast.makeText(activity,profileUserName,Toast.LENGTH_LONG).show()
                    Toast.makeText(activity,imageUrl,Toast.LENGTH_LONG).show()

                    Picasso.get()
                            .load(imageUrl)
                            .placeholder(R.drawable.blank_profile_pic)
                            .into(userProfilePic)
                }else{
                    Log.e("OnResponse : ","isn't successful")
                }
            }
        })
    }

    private fun updateUItoProfile(){
        val intent = Intent(activity, MyProfile::class.java)
        startActivity(intent)
    }

    private fun setTripDate(){
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(activity!!, android.R.style.Theme_Holo_Light_Dialog, mDateSetListener, year, month, day)
        datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}
