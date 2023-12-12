package com.example.geotag.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.R
import com.example.geotag.data.models.LoginModel
import com.example.geotag.data.response.LoginResponse
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.welcome.WelcomeActivity
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var backButton: ImageButton
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailLayout = findViewById(R.id.layout_email)
        passwordLayout = findViewById(R.id.layout_password)
        backButton = findViewById(R.id.back_btn)
        loginButton = findViewById(R.id.btn_login)

        backButton.setOnClickListener {
            // Handle the back button click
            navigateBackToWelcomeActivity()
        }

        loginButton.setOnClickListener {
            login()
        }
    }

    private fun login () {
        val email = emailLayout.editText.toString()
        val password = passwordLayout.editText.toString()

        val loginRequest = LoginModel(email, password)
        val call: Call<LoginResponse> = apiService.loginUser(loginRequest)

        fetch(call,
            success = { response, header ->
                if (header != null) {
                    if (response?.data !== null) {
                        showToast("Login successful")
                        navigateToHomeActivity()
                    }
                }
            },
            error = { code, message ->
                // Handle error
                showToast("Login failed: $message")
            }
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Override the back button press
        navigateBackToWelcomeActivity()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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