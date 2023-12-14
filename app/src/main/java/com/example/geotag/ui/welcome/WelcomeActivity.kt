package com.example.geotag.ui.welcome

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.geotag.R
import com.example.geotag.ui.login.LoginActivity
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val loginBtn = findViewById<Button>(R.id.button_login)
        val signupBtn = findViewById<Button>(R.id.button_signup)

        // Check login status
        if (isLoggedIn()) {
            // If the user is logged in, go to MainActivity
            navigateToMainActivity()
        }

        loginBtn.setOnClickListener {
            // Start LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signupBtn.setOnClickListener {
            // Start SignUpActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isLoggedIn(): Boolean {
        // Check if the user is already logged in
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", "")
        return !savedEmail.isNullOrEmpty()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the stack
    }
}