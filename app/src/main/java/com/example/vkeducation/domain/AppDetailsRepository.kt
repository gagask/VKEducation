package com.example.vkeducation.domain

interface AppDetailsRepository {
    suspend fun get(id: String): AppDetails
}