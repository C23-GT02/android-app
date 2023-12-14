package com.example.geotag.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geotag.R
import com.example.geotag.ui.history.HistoryActivity
import com.example.geotag.ui.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var botNavView: BottomNavigationView

    //TODO membuat profile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        botBarBind()
        botNavView.selectedItemId = R.id.bottom_profile
        botBarHandle()
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
                    loadActivities(HistoryActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_profile -> {
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
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }
}