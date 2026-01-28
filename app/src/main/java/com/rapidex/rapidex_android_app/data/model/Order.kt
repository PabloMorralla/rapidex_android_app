package com.rapidex.rapidex_android_app.data.model

import java.time.LocalDateTime

data class Order (
    val id: Int,
    val employee: Employee? = null,
    val preparationDate: LocalDateTime? = null,
    val dispatchDate: LocalDateTime? = null,
    val products: List<Product>
)
