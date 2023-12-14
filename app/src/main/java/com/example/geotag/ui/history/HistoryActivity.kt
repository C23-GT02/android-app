package com.example.geotag.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geotag.R
import com.example.geotag.data.adapter.HistoryAdapter
import com.example.geotag.data.models.HistoryModel
import com.example.geotag.data.response.ResponseHistory
import com.example.geotag.data.response.ResponseHistoryItem
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.profile.ProfileActivity
import com.example.geotag.ui.scan.ScanActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    private lateinit var botNavView: BottomNavigationView
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyRecyclerView = findViewById(R.id.history_rv)
        historyRecyclerView.setHasFixedSize(true)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        // Assuming you have a method to fetch history data from API
        fetchHistoryData()

        botBarBind()
        botNavView.selectedItemId = R.id.bottom_history
        botBarHandle()
    }

    private fun fetchHistoryData() {
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val dataHistory = HistoryModel(email!!)
        val fetchHistory: Call<List<ResponseHistoryItem>> = apiService.getHistory(dataHistory)

        fetch(
            fetchHistory,
            success = { historyList, _ ->
                if (historyList!!.isNotEmpty()) {
                    // Initialize and set the adapter after receiving the data
                    historyAdapter = historyList?.let { HistoryAdapter(this, it) }!!
                    historyRecyclerView.adapter = historyAdapter
                } else {
                    // Handle empty data scenario
                    Log.e("HistoryActivity", "ResponseHistory is empty")
                }
            },
            error = { code, message ->
                // Handle the error case
                showToast("API call failed: $message")
                // You can display an error message or take appropriate action
            }
        )
    }

    private fun botBarHandle() {
        botNavView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.bottom_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_scan -> {
                    loadActivities(ScanActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_history -> {
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_profile -> {
                    loadActivities(ProfileActivity())
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }

    private fun botBarBind() {
        botNavView = findViewById(R.id.bottomNav)
    }

    private fun loadActivities(activity: AppCompatActivity) {
        startActivity(Intent(applicationContext, activity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

//un-used
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//private fun getDummy(): ArrayList<Dummy> {
//        val datName = resources.getStringArray(R.array.data_name)
//        val datValDate = resources.getStringArray(R.array.data_valdate)
//        val datPic = resources.obtainTypedArray(R.array.data_pic)
//        val listDummys = ArrayList<Dummy>()
//        for (i in datName.indices){
//            val dat_dummys = Dummy(
//                datName[i],
//                datValDate[i],
//                datPic.getResourceId(i, -1,) )
//            listDummys.add(dat_dummys)
//        }
//        return  listDummys
//    }
//
//    private fun showRecyclerList() {
//        rvDatDum.layoutManager = LinearLayoutManager(this)
//        val listDummyAdapt = ListDummyAdapter(list)
//        rvDatDum.adapter = listDummyAdapt
//    }