package com.example.vkeducation.domain

import kotlinx.coroutines.flow.Flow


interface AppRepository {
    suspend fun getApps(): List<App>

    fun getAppDetails(id: String): Flow<AppDetails>

    suspend fun toggleWishlist(id: String)

    fun observeAppDetails(id: String): Flow<AppDetails>
}