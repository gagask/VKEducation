package com.example.vkeducation.domain

data class AppDetails(
    val id: String = "id",
    val name: String = "name",
    val developer: String = "developer",
    val category: String = "category",
    val ageRating: Int = 0,
    val size: Float = 0f,
    val iconUrl: String = "iconUrl",
    val screenshotUrlList: List<String> = emptyList(),
    val description: String = "description",
    val isInWishlist: Boolean = false
)