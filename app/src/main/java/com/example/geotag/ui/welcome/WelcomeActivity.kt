package com.example.geotag.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.geotag.R
import com.example.geotag.ui.login.LoginActivity
import com.example.geotag.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val loginBtn = findViewById<Button>(R.id.button_login)
        val signupBtn = findViewById<Button>(R.id.button_signup)

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
}