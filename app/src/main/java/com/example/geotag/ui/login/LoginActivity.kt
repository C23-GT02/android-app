package com.example.geotag.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geotag.R
import com.example.geotag.data.models.LoginModel
import com.example.geotag.data.response.LoginResponse
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.welcome.WelcomeActivity
import retrofit2.Call

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLayout: EditText
    private lateinit var passwordLayout: EditText
    private lateinit var backButton: ImageButton
    private lateinit var loginButton: Button
    private lateinit var emailUser: String

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailLayout = findViewById(R.id.input_email)
        passwordLayout = findViewById(R.id.input_password)
        backButton = findViewById(R.id.back_btn)
        loginButton = findViewById(R.id.btn_login)

        sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)

        // Check login status
        checkLoginStatus()

        backButton.setOnClickListener {
            navigateBackToWelcomeActivity()
        }

        loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = emailLayout.text.toString()
        val password = passwordLayout.text.toString()

        val loginRequest = LoginModel(email, password)
        val call: Call<LoginResponse> = apiService.loginUser(loginRequest)

        fetch(
            call,
            success = { response, header ->
                if (header != null) {
                    if (response?.data !== null) {
                        emailUser = response.data.email
                        showToast("Login successful")

                        // Save the email in SharedPreferences after successful login
                        saveEmailInPreferences(response)

                        navigateToHomeActivity()
                    } else {
                        showToast("Login failed: No data in the response")
                    }
                }
            },
            error = { _, message ->
                showToast("Login failed: $message")
            }
        )
    }

    private fun checkLoginStatus() {
        // Check if the email is already saved in SharedPreferences
        val savedEmail = sharedPreferences.getString("email", "")
        if (savedEmail != null) {
            if (savedEmail.isNotEmpty()) {
                // If there is a saved email, proceed to the home activity
                navigateToHomeActivity()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun saveEmailInPreferences(cookie: LoginResponse) {
        // Save the email in SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("data", cookie.data.toString())
        editor.putString("session", cookie.sessionCookie)
        editor.putString("email", cookie.data.email)
        editor.apply()
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
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