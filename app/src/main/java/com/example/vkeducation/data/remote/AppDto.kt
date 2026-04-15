package com.example.vkeducation.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class AppDto(
    val id: String = "id",
    val name: String = "name",
    val description: String = "description",
    val category: String = "category",
    val iconUrl: String = "iconUrl"
)