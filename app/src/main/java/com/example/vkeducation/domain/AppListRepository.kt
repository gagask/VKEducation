package com.example.vkeducation.domain

interface AppListRepository {
    suspend fun get(): List<AppCard>
}