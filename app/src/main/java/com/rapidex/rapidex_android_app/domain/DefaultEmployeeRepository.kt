package com.rapidex.rapidex_android_app.domain

import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.data.service.RapidexService
import java.io.IOException

class DefaultEmployeeRepository(
    val service: RapidexService
): EmployeeRepository {
    override var employee: Employee? = null

    override suspend fun login(email: String, password: String): Employee? {
        try {
            val employee = service.login(email, password)

            if (employee == null) {
                this.employee = null
                return null
            }
            else {
                this.employee = employee
                return employee
            }
        }
        catch (e: IOException) {
            throw e
        }
    }
}