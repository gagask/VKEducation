package com.example.vkeducation.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_details")
data class AppDetailsEntity(
    @PrimaryKey
    val id: String = "id",
    val name: String = "name",
    val developer: String = "developer",
    val category: String = "category",
    val ageRating: Int = 0,
    val size: Float = 0f,
    val iconUrl: String = "iconUrl",
    val screenshots: List<String> = emptyList(),
    val description: String = "description",
    val lastUpdated: Long = System.currentTimeMillis(),
    val isInWishlist: Boolean = false
)
