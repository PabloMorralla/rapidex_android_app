package com.rapidex.rapidex_android_app.data.model

import com.google.gson.annotations.SerializedName

data class Employee (
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String?
)
