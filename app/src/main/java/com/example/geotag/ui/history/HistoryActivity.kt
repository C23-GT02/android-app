package com.example.geotag.ui.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R
import com.example.geotag.data.adapter.HistoryAdapter
import com.example.geotag.data.response.ResponseHistory
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.profile.ProfileActivity
import com.example.geotag.ui.scan.ScanActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryActivity : AppCompatActivity() {
    private lateinit var botNavView: BottomNavigationView
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyRecyclerView = findViewById(R.id.history_rv)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        // Assuming you have a method to fetch history data from API
        fetchHistoryData()
        botBarBind()
        botNavView.selectedItemId = R.id.bottom_history
        botBarHandle()
    }

    private fun fetchHistoryData() {
        val call = apiService.getHistory()
        fetch(call, this::handleHistorySuccess, this::handleError)
    }

    private fun handleHistorySuccess(history: ResponseHistory?, headers: Map<String, String>?) {
        history?.let {
            if (it.responseHistory.isNotEmpty()) {
                // Initialize and set the adapter after receiving the data
                historyAdapter = HistoryAdapter(this, it.responseHistory)
                historyRecyclerView.adapter = historyAdapter
            } else {
                // Handle empty data scenario
                Log.e("HistoryActivity", "ResponseHistory is empty")
            }
        } ?: run {
            // Handle the case where history is null
            Log.e("HistoryActivity", "ResponseHistory is null")
        }
    }

    private fun handleError(statusCode: Int, errorMessage: String) {
        // Handle error
        Log.e("HistoryActivity", "Error: $statusCode - $errorMessage")
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