package com.rapidex.rapidex_android_app.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Order (
    @SerializedName("id") val id: Int,
    @SerializedName("employee") val employee: Employee? = null,
    @SerializedName("prepDate") val preparationDate: LocalDateTime? = null,
    @SerializedName("dispatchDate") val dispatchDate: LocalDateTime? = null,
    @SerializedName("products") val products: List<Product>
)
