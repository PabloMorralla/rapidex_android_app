package com.rapidex.rapidex_android_app.data.model

data class Product (
    val id: Int,
    val productName: String,
    val productCategory: String,
    val description: String? = null,
)
