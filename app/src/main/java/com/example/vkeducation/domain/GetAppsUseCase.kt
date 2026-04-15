package com.example.vkeducation.domain

import jakarta.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<App> {
        return repository.getApps()
    }
}