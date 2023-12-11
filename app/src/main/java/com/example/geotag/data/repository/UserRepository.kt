package com.example.geotag.data.repository
//
//import androidx.lifecycle.liveData
//import com.example.justtesting.pref.UserModel
//import com.example.justtesting.pref.UserPref
//import com.example.justtesting.response.LoginResponse
//import com.example.justtesting.response.SignUpResponse
//import com.example.justtesting.retrofit.ApiService
//import com.google.gson.Gson
//import kotlinx.coroutines.flow.Flow
//import retrofit2.HttpException
//
//
//class UserRepository (
//    private val userPreference: UserPref,
//    private val apiService: ApiService
//) {
//
//    suspend fun saveSession(user: UserModel) {
//        userPreference.saveSession(user)
//    }
//
//    fun getSession(): Flow<UserModel> {
//        return userPreference.getSession()
//    }
//
//    suspend fun logout() {
//        userPreference.logout()
//    }
//
//    fun register(name: String, email: String, password: String) = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.register(name, email, password)
//            emit(Result.Success(response))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, SignUpResponse::class.java)
//            emit(Result.Error(errorResponse.message))
//        }
//    }
//
//    fun login( email: String, password: String) = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.login(email, password)
//            emit(Result.Success(response))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
//            emit(Result.Error(errorResponse.message))
//        }
//    }
//
//    companion object {
//        fun getInstance(
//            userPreference: UserPref,
//            apiService: ApiService
//        ): UserRepository = UserRepository(userPreference, apiService)
//    }
//}