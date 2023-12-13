package com.example.geotag.data.dummys

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


//TODO ambil history dari API
@Parcelize
data class Dummy (
    val name: String,
    val val_date: String,
    val images: Int,
) : Parcelable