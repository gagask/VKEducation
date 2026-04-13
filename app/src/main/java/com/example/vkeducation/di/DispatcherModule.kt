package com.example.vkeducation.di

import com.example.vkeducation.core.dispatchers.DefaultDispatcherProvider
import com.example.vkeducation.core.dispatchers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()
}