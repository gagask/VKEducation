package com.example.vkeducation.domain

import jakarta.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: String) {
        return repository.toggleWishlist(id)
    }
}