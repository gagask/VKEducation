package com.example.vkeducation.data

import com.example.vkeducation.domain.Category
import jakarta.inject.Inject

class CategoryMapper @Inject constructor() {
    fun toDomain(category: String) = when(category) {
        "App" -> Category.APP
        "Game" -> Category.GAME
        else -> throw IllegalStateException("Unsupported category type: $category")
    }
}