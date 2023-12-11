package com.example.geotag.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.R
import com.example.geotag.ui.welcome.WelcomeActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        val loginBtn = findViewById<Button>(R.id.btn_login)

        backButton.setOnClickListener {
            // Handle the back button click
            navigateBackToWelcomeActivity()
        }

        loginBtn.setOnClickListener {
            // Handle the login button click
            navigateToHomeActivity()
        }
    }

    private fun login () {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Override the back button press
        navigateBackToWelcomeActivity()
    }

    private fun navigateBackToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the stack
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the stack
    }
}