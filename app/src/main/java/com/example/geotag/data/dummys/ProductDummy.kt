package com.example.geotag.data.dummys

import android.os.Parcel
import android.os.Parcelable

data class ProductDummy(
    val productTitle: String,
    val productDescription: String,
    val productLogo: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productTitle)
        parcel.writeString(productDescription)
        parcel.writeInt(productLogo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDummy> {
        override fun createFromParcel(parcel: Parcel): ProductDummy {
            return ProductDummy(parcel)
        }

        override fun newArray(size: Int): Array<ProductDummy?> {
            return arrayOfNulls(size)
        }
    }
}
