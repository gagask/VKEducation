package com.example.vkeducation.di

import com.example.vkeducation.data.AppRepositoryImpl
import com.example.vkeducation.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppListRepository(
        impl: AppRepositoryImpl
    ): AppRepository
}