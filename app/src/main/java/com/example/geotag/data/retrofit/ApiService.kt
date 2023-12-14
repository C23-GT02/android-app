package com.example.geotag.data.retrofit

import com.example.geotag.data.models.LoginModel
import com.example.geotag.data.models.RegisterModel
import com.example.geotag.data.models.UserDataProfile
import com.example.geotag.data.response.HomeResponse
import com.example.geotag.data.response.LoginResponse
import com.example.geotag.data.response.Product
import com.example.geotag.data.response.ResponseHistory
import com.example.geotag.data.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("auth/register")
    fun registerUser(
        @Body request: RegisterModel
    ): Call<SignUpResponse>

    @POST("auth/login")
    fun loginUser(
        @Body request: LoginModel
    ): Call<LoginResponse>

    //HOME ROUTER
    @GET("home")
    fun getHome(): Call<HomeResponse>

    //History
    @GET("history")
    fun getHistory(): Call<ResponseHistory>

    @GET("qr")
    fun scanQr(@Query("productRef") ref: String): Call<Product>

    // User Profile
    @GET("user")
    fun getUserData(@Query("email") email: String): Call<UserDataProfile>
}