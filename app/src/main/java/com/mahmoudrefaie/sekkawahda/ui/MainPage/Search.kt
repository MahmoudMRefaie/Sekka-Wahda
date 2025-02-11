package com.mahmoudrefaie.sekkawahda.ui.MainPage

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.Pojo.Trip
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.MainPage.NavigationFragments.Home.PostsListAdapter
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Search : AppCompatActivity(), View.OnClickListener {

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        appAuth = getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        val fromCityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        fromCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        from_city.adapter = fromCityAdapter
        from_city.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                (parentView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })

        val toCityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        toCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        to_city.adapter = toCityAdapter
        to_city.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                (parentView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })

        date_layout.setOnClickListener(this)
        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month++
            var editedMonth: String? = null
            var editedDay: String? = null
            if (month < 10)
                editedMonth = "0$month"
            else
                editedMonth = month.toString()
            if (day < 10)
                editedDay = "0$day"
            else
                editedDay = day.toString()
            //val date = "$day/$month/$year"
            val d = "$year-$editedMonth-$editedDay"
            show_date.setText(d)
        }

        search_btn.setOnClickListener(this)
        refreshLayout.setColorSchemeResources(R.color.proj_sub_color)
        refreshLayout.setOnRefreshListener {
            getTrips(from_city.selectedItem.toString(),to_city.selectedItem.toString(),show_date.text.toString(),accessToken!!)
        }

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.date_layout){
            setTripDate()
        }
        else if(v?.id == R.id.search_btn){
            val getFromCity = from_city.selectedItem.toString()
            val getToCity = to_city.selectedItem.toString()
            val getDate = show_date.text.toString()
            getTrips(getFromCity, getToCity, getDate, accessToken!!)
        }
    }

    fun getTrips(fromCity: String, toCity: String, tripDate: String, authToken: String){
        refreshLayout.isRefreshing = true
        val call: Call<List<Trip>>? = RetrofitClient.instance?.api?.search(fromCity,toCity,tripDate,"Bearer $authToken")
        call?.enqueue(object : Callback<List<Trip>> {
            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Log.e("onFailure loading posts", t.message!!)
                Toast.makeText(applicationContext, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                if (response.isSuccessful) {
                    refreshLayout.isRefreshing = false
                    var tripData: List<Trip> = response.body()!!
                    Log.e("OnResponse : ", tripData.toString())
                    //Toast.makeText(activity,tripData.toString(),Toast.LENGTH_LONG).show()
                    showTripData(this@Search, tripData)
                } else {
                    refreshLayout.isRefreshing = false
                    Log.e("OnResponse : ", response.body().toString())
                    Toast.makeText(this@Search, response.body().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(this@Search, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun showTripData(context: Context, trips: List<Trip>){
        search_result.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = PostsListAdapter(context, trips)
        }
    }

    private fun setTripDate(){
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, mDateSetListener, year, month, day)
        datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()
    }
}