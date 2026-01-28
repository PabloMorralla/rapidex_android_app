package com.rapidex.rapidex_android_app.hilt

import com.rapidex.rapidex_android_app.data.service.RapidexService
import com.rapidex.rapidex_android_app.data.service.TestRapidexService
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
        return TestRapidexService()
    }
}
