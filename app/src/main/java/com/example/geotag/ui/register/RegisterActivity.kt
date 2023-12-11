package com.example.geotag.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.geotag.R
import com.example.geotag.ui.login.LoginActivity
import com.example.geotag.data.models.RegisterModel
import com.example.geotag.data.response.SignUpResponse
import com.example.geotag.data.retrofit.RetrofitClient
import com.example.geotag.data.retrofit.ApiService
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.welcome.WelcomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var firstnameEditText: EditText
    private lateinit var lastnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val backButton = findViewById<ImageButton>(R.id.back_btn2)

        // Initialize UI components
        firstnameEditText = findViewById(R.id.input_firstname)
        lastnameEditText = findViewById(R.id.input_lastname)
        emailEditText = findViewById(R.id.input_emailreg)
        passwordEditText = findViewById(R.id.input_passwordreg)
        registerButton = findViewById(R.id.btn_registerbtn)

        // Set click listener for the register button
        registerButton.setOnClickListener {
            register()
        }

        backButton.setOnClickListener {
            // Handle the back button click
            navigateBackToWelcomeActivity()
        }
    }
    private fun register() {
        // Get values from EditText fields
        val firstname = firstnameEditText.text.toString()
        val lastname = lastnameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Perform the registration API call
        val registrationRequest = RegisterModel(
            firstname = firstname,
            lastname = lastname,
            email = email,
            password = password
        )

        val call: Call<SignUpResponse> = apiService.registerUser(registrationRequest)

        fetch(call,
            success = { response ->
                println(response)
                showToast("Registration successful")
                navigateToLoginActivity()
            },
            error = { code, message ->
                // Handle error
                showToast("Registration failed: $message")
            }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the stack
    }
}