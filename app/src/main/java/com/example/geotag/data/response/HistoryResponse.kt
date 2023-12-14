package com.example.geotag.data.response

import com.google.gson.annotations.SerializedName

data class ResponseHistory(

    @field:SerializedName("ResponseHistory")
    val responseHistory: List<ResponseHistoryItem>
)

data class ResponseHistoryItem(

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("review")
    val review: String,

    @field:SerializedName("productRef")
    val productRef: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("timestamp")
    val timestamp: String
)