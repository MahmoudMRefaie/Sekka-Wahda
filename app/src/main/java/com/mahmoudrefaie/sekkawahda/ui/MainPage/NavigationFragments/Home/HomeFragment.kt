package com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home

import android.app.ActionBar
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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.Pojo.UserIdResponse
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Login.Login
import com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile.MyProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() , View.OnClickListener {
    private var userProfilePic: CircleImageView? = null
    private var username: TextView? = null
    private var tripDate: TextView? = null
    private var mDateSetListener: OnDateSetListener? = null
    private var tripTime: TextView? = null
    private var mTimeSetListener: OnTimeSetListener? = null
    private var placeToMeet : EditText? = null
    private var writePost: TextView? = null
    private var extendedPost: RelativeLayout? = null
    private var createPost: RelativeLayout? = null
    private var submitPost: Button? = null
    private var toCity: Spinner? = null
    private var fromCity: Spinner? = null
    lateinit var recycler: RecyclerView
    lateinit var recyclerProgressBar: ProgressBar
    lateinit var refreshLayout: SwipeRefreshLayout

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    private var sharedPre: SharedPreferences? = null

    private var homeViewModel : HomeViewModel? = null

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
        userProfilePic?.setOnClickListener(this)
        username?.setOnClickListener(this)

        //DatePicker Dialog
        tripDate = view.findViewById(R.id.date)
        tripDate?.setOnClickListener(this)
        mDateSetListener = OnDateSetListener { datePicker, year, month, day ->
//            var monthInAlpha = ""
//            when (month) {
//                1 -> monthInAlpha = "Jan"
//                2 -> monthInAlpha = "Feb"
//                3 -> monthInAlpha = "Mar"
//                4 -> monthInAlpha = "Abr"
//                5 -> monthInAlpha = "May"
//                6 -> monthInAlpha = "Jun"
//                7 -> monthInAlpha = "Jul"
//                8 -> monthInAlpha = "Aug"
//                9 -> monthInAlpha = "Sep"
//                10 -> monthInAlpha = "Oct"
//                11 -> monthInAlpha = "Nov"
//                12 -> monthInAlpha = "Dec"
//            }
            var month = month
            month++
            var editedMonth: String? = null
            var editedDay: String? = null
            if(month<10)
                editedMonth = "0$month"
            else
                editedMonth = month.toString()
            if(day<10)
                editedDay = "0$day"
            else
                editedDay = day.toString()

            //val date = "$day/$month/$year"
            val d = "$year-$editedMonth-$editedDay"
            tripDate?. text = d
        }

        //TimePicker Dialog
        tripTime = view.findViewById(R.id.time)
        tripTime?.setOnClickListener(this)

        mTimeSetListener = OnTimeSetListener { timePicker, hour, minute ->
//            var minute = minute
//            minute += 1
            var editedHour: String? = null
            var editedMins: String? = null

            if(hour<10)
                editedHour = "0$hour"
            else
                editedHour = hour.toString()
            if(minute<10)
                editedMins = "0$minute"
            else
                editedMins = minute.toString()

            val time = "$editedHour:$editedMins"
            tripTime?.setText(time)
        }

        placeToMeet = view.findViewById(R.id.place_to_meet)

        //Resize create post layour
        createPost = view.findViewById(R.id.create_post) as RelativeLayout
        //createPost!!.layoutParams.height = 400

        //OnClick write a post
        writePost = view.findViewById(R.id.writePost)
        extendedPost = view.findViewById(R.id.extended_post)
        writePost?.setOnClickListener(this)

        //Making a spinner
        toCity = view.findViewById(R.id.to_city) as Spinner //ToCity Spinner
        val toCityAdapter = ArrayAdapter.createFromResource(context!!,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        toCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        toCity?.adapter = toCityAdapter

        fromCity = view.findViewById(R.id.from_city) as Spinner //FromCity Spinner
        val fromCityAdapter = ArrayAdapter.createFromResource(context!!,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        fromCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromCity?.adapter = fromCityAdapter

        //Submit Post
        submitPost = view.findViewById(R.id.submit_post)
        submitPost?.setOnClickListener(this)

        //using MVVM
        //homeViewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
        //homeViewModel?.getTrips(accessToken!!)

        //Recycler View
        recycler = view.findViewById(R.id.posts_recycler) as RecyclerView
        recyclerProgressBar = view.findViewById(R.id.trips_progress_bar)
        //val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        //recycler.layoutManager = layoutManager

        refreshLayout = view.findViewById(R.id.refreshLayout)
        refreshLayout.setColorSchemeResources(R.color.proj_sub_color)
        refreshLayout.setOnRefreshListener {
            getTrips(accessToken!!)
            getUserImageandName(accessToken!!)
        }

        getTrips(accessToken!!)
//        homeViewModel?.postsMutableLiveData?.observe(activity!!, object : Observer<List<Trip>>{
//            override fun onChanged(trip:List<Trip>?) {
//                adapter.setList(trip!!)
//            }
//        })

        return view
    }

    fun getTrips(authToken: String){
        refreshLayout.isRefreshing = true
        val call: Call<List<Trip>>? = RetrofitClient.instance?.api?.getTrips("Bearer $authToken")
        call?.enqueue(object : Callback<List<Trip>> {
            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Log.e("onFailure loading posts", t.message)
                Toast.makeText(activity, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                if (response.isSuccessful) {
                    refreshLayout.isRefreshing = false
                    var tripData: List<Trip> = response.body()!!
                    Log.e("OnResponse : ", tripData.toString())
                    //Toast.makeText(activity,tripData.toString(),Toast.LENGTH_LONG).show()
                    showTripData(activity!!, tripData)
                    recyclerProgressBar.visibility = View.INVISIBLE
                } else {
                    refreshLayout.isRefreshing = false
                    Log.e("OnResponse : ", response.body().toString())
                    Toast.makeText(activity, response.body().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(activity, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showTripData(context: Context, trips: List<Trip>){
        recycler!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PostsListAdapter(context, trips)
        }
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.writePost){
            writePost?.setVisibility(View.INVISIBLE) //making write post TextView invisible
            extendedPost?.setVisibility(View.VISIBLE) //making extended post layout visible
            createPost!!.layoutParams = ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) //Resize createpost layout on click
        }
        else if(view?.id == R.id.submit_post){
            val selectedFromCity = fromCity?.selectedItem.toString()
            val selectedToCity = toCity?.selectedItem.toString()
            val selectedTime = tripTime?.text.toString()
            val selectedDate = tripDate?.text.toString()
            val selectedPlaceToMeet = placeToMeet?.text.toString()
            createTrip(selectedFromCity, selectedToCity, selectedPlaceToMeet, selectedDate, selectedTime, accessToken!!)
        }
        else if(view?.id == R.id.profPicUser || view?.id == R.id.username){
            updateUItoProfile()
        }
        else if(view?.id == R.id.date){
            setTripDate()
        }
        else if(view?.id == R.id.time){
            setTripTime()
        }

    }

    //Getting user id , profile picture , username
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

                    username?.text = profileUserName

                    imageName = imageName?.replace("~/", "")
                    val imageUrl = "https://seka.azurewebsites.net/$imageName"
                    Log.e("Image URL ", imageUrl)
                    Picasso.get()
                            .load(imageUrl)
                            .placeholder(R.drawable.blank_profile_pic)
                            .into(userProfilePic, object : com.squareup.picasso.Callback {
                                override fun onSuccess() {
                                    Log.e("CallBack ", "onSuccess")
                                }

                                override fun onError(e: Exception?) {
                                    userProfilePic?.setImageResource(R.drawable.blank_profile_pic);
                                    Log.e("CallBack ", e?.message)
                                }
                            })

                } else {
                    Log.e("OnResponse : ", "isn't successful")
                }
            }
        })
    }

    private fun createTrip(fromCity: String, toCity: String, placeToMeet: String, tripDate: String, tripTime: String, authToken: String) {
        val call: Call<String>? = RetrofitClient.instance?.api?.postTrip(
                fromCity, toCity, placeToMeet, tripDate, tripTime, "Bearer $authToken"
        )
        call?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                //Toast.makeText(this@Profile, "Check your connection", Toast.LENGTH_LONG).show()
                Toast.makeText(activity, "OnFailure : " + t.message, Toast.LENGTH_LONG).show()
                Log.e("onFailure post trip : ", t.message)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "Trip posted successfully", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, "You should enter your car details and your driving License first to make a post",
                            Toast.LENGTH_LONG).show()
                    Log.e("OnResponse : ", response.body().toString())
                    updateUItoProfile()
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
    private fun setTripTime(){
        val cal = Calendar.getInstance()
        val hour = cal[Calendar.HOUR]
        val minute = cal[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog, mTimeSetListener, hour, minute, true)
        timePickerDialog.show()
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
