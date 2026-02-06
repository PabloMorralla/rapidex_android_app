package com.rapidex.rapidex_android_app.hilt

import com.rapidex.rapidex_android_app.data.api.RetrofitClient
import com.rapidex.rapidex_android_app.data.service.NetworkRapidexService
import com.rapidex.rapidex_android_app.data.service.RapidexService
import com.rapidex.rapidex_android_app.data.service.TestRapidexService
import com.rapidex.rapidex_android_app.domain.DefaultEmployeeRepository
import com.rapidex.rapidex_android_app.domain.DefaultIncidentRepository
import com.rapidex.rapidex_android_app.domain.DefaultOrderRepository
import com.rapidex.rapidex_android_app.domain.EmployeeRepository
import com.rapidex.rapidex_android_app.domain.IncidentRepository
import com.rapidex.rapidex_android_app.domain.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RapidexModule {
    @Provides
    @Singleton
    fun providesService(): RapidexService {
        return NetworkRapidexService(RetrofitClient.apiService)
    }

    @Provides
    @Singleton
    fun providesEmployeeRepository(): EmployeeRepository {
        return DefaultEmployeeRepository(providesService())
    }

    @Provides
    @Singleton
    fun providesOrderRepository(): OrderRepository {
        return DefaultOrderRepository(providesService())
    }

    @Provides
    @Singleton
    fun providesIncidentRepository(): IncidentRepository {
        return DefaultIncidentRepository(providesService())
    }
}
