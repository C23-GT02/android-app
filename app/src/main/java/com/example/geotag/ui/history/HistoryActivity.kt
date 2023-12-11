package com.example.geotag.ui.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R
import com.example.geotag.data.adapter.ListDummyAdapter
import com.example.geotag.data.dummys.Dummy
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryActivity : AppCompatActivity() {
    private lateinit var rvDatDum: RecyclerView
    private val list = ArrayList<Dummy>()
    private lateinit var botNavView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        rvDatDum = findViewById(R.id.history_rv)
        rvDatDum.setHasFixedSize(true)

        list.addAll(getDummy())
        showRecyclerList()
        botBarBind()
        botNavView.selectedItemId = R.id.bottom_history
        botBarHandle()
    }

    private fun getDummy(): ArrayList<Dummy> {
        val datName = resources.getStringArray(R.array.data_name)
        val datValDate = resources.getStringArray(R.array.data_valdate)
        val datPic = resources.obtainTypedArray(R.array.data_pic)
        val listDummys = ArrayList<Dummy>()
        for (i in datName.indices){
            val dat_dummys = Dummy(
                datName[i],
                datValDate[i],
                datPic.getResourceId(i, -1,) )
            listDummys.add(dat_dummys)
        }
        return  listDummys
    }

    private fun showRecyclerList() {
        rvDatDum.layoutManager = LinearLayoutManager(this)
        val listDummyAdapt = ListDummyAdapter(list)
        rvDatDum.adapter = listDummyAdapt
    }

    private fun botBarHandle() {
        botNavView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.bottom_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

//                R.id.bottom_scan -> {
//                    loadActivities(())
//                    return@setOnItemSelectedListener true
//                }

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