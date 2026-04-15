package com.example.vkeducation.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto(
    val id: String = "id",
    val name: String = "name",
    val developer: String = "developer",
    val category: String = "category",
    val ageRating: Int = 0,
    val size: Float = 0f,
    val iconUrl: String = "iconUrl",
    val screenshotUrlList: List<String> = emptyList(),
    val description: String = "description",
)