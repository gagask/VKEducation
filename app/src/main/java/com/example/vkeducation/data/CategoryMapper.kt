package com.example.vkeducation.data

import com.example.vkeducation.domain.Category

class CategoryMapper {
    fun toDomain(category: String) = when(category) {
        "App" -> Category.APP
        "Game" -> Category.GAME
        else -> throw IllegalStateException("Unsupported category type: $category")
    }
}