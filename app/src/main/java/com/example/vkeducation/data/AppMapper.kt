package com.example.vkeducation.data

import com.example.vkeducation.data.dto.AppDto
import com.example.vkeducation.domain.App
import jakarta.inject.Inject

class AppMapper @Inject constructor() {
    fun toDomain(dto: AppDto) = App(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = dto.category,
        iconUrl = dto.iconUrl
    )
}