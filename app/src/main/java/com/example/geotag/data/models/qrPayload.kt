package com.example.geotag.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QrPayload (
    val id: String,
    val name: String,
    val partnerRef: String,
    val productRef: String,
) : Parcelable