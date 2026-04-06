package com.example.vkeducation.domain



interface AppRepository {
    suspend fun getApps(): List<App>

    suspend fun getAppDetails(id: String): AppDetails
}