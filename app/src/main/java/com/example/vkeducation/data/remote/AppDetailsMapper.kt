package com.example.vkeducation.data.remote

import com.example.vkeducation.domain.AppDetails
import jakarta.inject.Inject

class AppDetailsMapper @Inject constructor() {
    fun toDomain(dto: AppDetailsDto) = AppDetails(
        id = dto.id,
        name = dto.name,
        developer = dto.developer,
        category = dto.category,
        ageRating = dto.ageRating,
        size = dto.size,
        iconUrl = dto.iconUrl,
        screenshotUrlList = dto.screenshotUrlList,
        description = dto.description
    )
}