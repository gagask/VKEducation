package com.example.vkeducation.domain



interface AppRepository {
    suspend fun getApps(): List<App>

    suspend fun getApp(id: String): AppDetails
}