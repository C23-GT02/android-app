package com.example.geotag.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geotag.R
import com.example.geotag.data.models.RegisterModel
import com.example.geotag.data.response.SignUpResponse
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.login.LoginActivity
import com.example.geotag.ui.welcome.WelcomeActivity
import retrofit2.Call

class RegisterActivity : AppCompatActivity() {
    private lateinit var firstnameEditText: EditText
    private lateinit var lastnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var progressBar: ProgressBar  // Add this line

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
        progressBar = findViewById(R.id.progres_bar)  // Add this line

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

        // Show ProgressBar while registering
        progressBar.visibility = View.VISIBLE

        // Perform the registration API call

        try {
            // Perform the registration API call
            val registrationRequest = RegisterModel(
                firstname = firstname,
                lastname = lastname,
                email = email,
                password = password
            )

            val call: Call<SignUpResponse> = apiService.registerUser(registrationRequest)

            fetch(
                call,
                success = { response, header ->
                    println(response)
                    showToast("Registration successful")
                    navigateToLoginActivity()
                },
                error = { code, message ->
                    // Handle error
                    showToast("Registration failed: $message")
                }
            )
        } finally {
            // Hide ProgressBar when the registration process is complete
            progressBar.visibility = View.GONE
        }

//        val registrationRequest = RegisterModel(
//            firstname = firstname,
//            lastname = lastname,
//            email = email,
//            password = password
//        )
//
//        val call: Call<SignUpResponse> = apiService.registerUser(registrationRequest)
//
//        fetch(call,
//            success = { response, header ->
//                println(response)
//                showToast("Registration successful")
//                navigateToLoginActivity()
//            },
//            error = { code, message ->
//                // Handle error
//                showToast("Registration failed: $message")
//            },
//            finally = {
//                // Hide ProgressBar when the registration process is complete
//                progressBar.visibility = View.GONE
//            }
//        )
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

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the stack
    }
}