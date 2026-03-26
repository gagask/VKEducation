package com.example.vkeducation.di

import com.example.vkeducation.data.AppListMockRepositoryImpl
import com.example.vkeducation.domain.AppListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppListRepository(
        impl: AppListMockRepositoryImpl
    ): AppListRepository
}