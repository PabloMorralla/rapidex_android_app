package com.rapidex.rapidex_android_app.data.model

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id") val id: Int,
    @SerializedName("productName") val name: String,
    @SerializedName("productCategory") val category: String,
    @SerializedName("productDescription") val description: String? = null,
    val done: Boolean? = null
)
