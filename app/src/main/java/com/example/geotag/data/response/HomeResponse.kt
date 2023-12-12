package com.example.geotag.data.response

import com.google.gson.annotations.SerializedName

data class HomeResponse (

    @field:SerializedName("productCollection")
    val productCollection: List<ProductCollectionItem>,

    @field:SerializedName("partnerCollection")
    val partnerCollection: List<PartnerCollectionItem>
)

data class ProductCollectionItem(

    @field:SerializedName("product")
    val product: Product
)

data class PartnerCollectionItem(

    @field:SerializedName("displayName")
    val displayName: String,

    @field:SerializedName("nib")
    val nib: String,

    @field:SerializedName("lastUpdate")
    val lastUpdate: String,

    @field:SerializedName("businessName")
    val businessName: String,

    @field:SerializedName("logo")
    val logo: String,

    @field:SerializedName("telephone")
    val telephone: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("olshopUrl")
    val olshopUrl: String,

    @field:SerializedName("uuid")
    val uuid: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("timestamp")
    val timestamp: String
)

data class Product(

    @field:SerializedName("images")
    val images: List<String>,

    @field:SerializedName("harga")
    val harga: Int,

    @field:SerializedName("material")
    val material: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("kategori")
    val kategori: String,

    @field:SerializedName("packaging")
    val packaging: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("stock")
    val stock: Int,

    @field:SerializedName("proses")
    val proses: String,

    @field:SerializedName("tags")
    val tags: List<String>,

    @field:SerializedName("qrcodeURL")
    val qrcodeURL: String
)