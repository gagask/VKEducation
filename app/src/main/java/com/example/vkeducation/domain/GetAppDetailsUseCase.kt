package com.example.vkeducation.domain

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(id: String): Flow<AppDetails> {
        return repository.getAppDetails(id)
    }
}