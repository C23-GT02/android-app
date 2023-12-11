package com.example.geotag.data.retrofit

import com.example.geotag.data.pref.RegisterModel
import com.example.geotag.data.response.SignUpResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body


interface ApiService {
    @POST("auth/register")
    fun registerUser(
        @Body request: RegisterModel
    ): Call<SignUpResponse>
}