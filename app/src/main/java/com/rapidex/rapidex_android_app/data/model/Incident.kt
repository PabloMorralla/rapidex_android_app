package com.rapidex.rapidex_android_app.data.model

import com.google.gson.annotations.SerializedName

data class Incident (
    @SerializedName("id") val id: Int,
    @SerializedName("type") val typeString: String,
    @SerializedName("description") val description: String?,
    @SerializedName("orderId") val orderId: Int,
    @SerializedName("employeeId") val employeeId: Int,
    @SerializedName("employeeName") val employeeName: String
)