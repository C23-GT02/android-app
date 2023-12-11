package com.example.geotag.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.R
import com.example.geotag.ui.welcome.WelcomeActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        val loginBtn = findViewById<Button>(R.id.btn_login)
        emailLayout = findViewById(R.id.layout_email)
        passwordLayout = findViewById(R.id.layout_password)

        backButton.setOnClickListener {
            // Handle the back button click
            navigateBackToWelcomeActivity()
        }

        val loginButton: Button = findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val email = emailLayout.editText?.text.toString()
        val password = passwordLayout.editText?.text.toString()

        // Perform your login logic here
        // You can add validation and authentication code

        // For example, displaying a toast message
        if (email.isNotEmpty() && password.isNotEmpty()) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter valid email and password", Toast.LENGTH_SHORT).show()
        }
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