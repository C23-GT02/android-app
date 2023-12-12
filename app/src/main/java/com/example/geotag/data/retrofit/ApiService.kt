package com.example.geotag.data.retrofit

import com.example.geotag.data.models.LoginModel
import com.example.geotag.data.models.RegisterModel
import com.example.geotag.data.response.LoginResponse
import com.example.geotag.data.response.SignUpResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body


interface ApiService {
    @POST("auth/register")
    fun registerUser(
        @Body request: RegisterModel
    ): Call<SignUpResponse>

    @POST("auth/login")
    fun loginUser(
        @Body request: LoginModel
    ): Call<LoginResponse>
}