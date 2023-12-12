package com.example.geotag.data.response


data class LoginResponse (
    val data: LoginResponseData,
    val sessionCookie: String,
)

data class LoginResponseData(
    val idToken: String,
    val uid: String,
    val businessName: String,
    val email: String,
    val role: String
)