package com.rapidex.rapidex_android_app.domain

import com.rapidex.rapidex_android_app.data.model.Employee

interface EmployeeRepository {
    var employee: Employee?

    /**
     * Attempts a login
     * @returns The Employee object on successful login, and null on incorrect credentials
     * @throws java.io.IOException on network error
     */
    suspend fun login(email: String, password: String): Employee?
}
