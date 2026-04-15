package com.example.vkeducation.domain

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveAppDetailsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(id: String): Flow<AppDetails> {
        return repository.observeAppDetails(id)
    }
}