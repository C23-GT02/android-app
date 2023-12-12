package com.example.geotag.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

import com.example.geotag.ui.main.MainActivity
import com.example.geotag.R
import com.example.geotag.data.models.LoginRequest
import com.example.geotag.data.response.LoginResponse
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.welcome.WelcomeActivity
import com.google.android.material.textfield.TextInputLayout

import retrofit2.Call

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLayout: EditText
    private lateinit var passwordLayout: EditText
    private lateinit var backButton: ImageButton
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        emailLayout = findViewById(R.id.input_email)
        passwordLayout = findViewById(R.id.input_password)
        backButton = findViewById(R.id.back_btn)
        loginButton = findViewById(R.id.btn_login)


        backButton.setOnClickListener {
            // Handle the back button click
            navigateBackToWelcomeActivity()
        }

        loginButton.setOnClickListener {
            // Handle the login button click
            // navigateToHomeActivity()
            login()
        }
    }

    private fun login () {
        
        val email = emailLayout.text.toString()
        val password = passwordLayout.text.toString()

        val loginRequest = LoginRequest(email, password)
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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        super.onBackPressed()
        // Handle the back button press
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