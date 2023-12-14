package com.example.geotag.ui.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.geotag.R
import com.example.geotag.ui.history.HistoryActivity
import com.example.geotag.ui.login.LoginActivity
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.scan.ScanActivity
import com.example.geotag.ui.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var botNavView: BottomNavigationView
    private lateinit var logoutText: TextView

    //TODO membuat fungsi ketika logout di klik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        botBarBind()
        botNavView.selectedItemId = R.id.bottom_profile
        botBarHandle()

        logoutText = findViewById(R.id.logout_tv)

        logoutText.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        // Clear user preferences
        clearUserPreferences()

        // Navigate to the login or splash screen
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
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
        finish()
    }

    private fun clearUserPreferences() {
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}