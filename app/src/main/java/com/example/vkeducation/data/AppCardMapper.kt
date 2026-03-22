package com.example.vkeducation.data

import com.example.vkeducation.domain.AppCard

class AppCardMapper(
    private val appTypeMapper: AppTypeMapper
) {
    fun toDomain(dto: AppCardDto) = AppCard(
        image = dto.image,
        name = dto.name,
        shortDescription = dto.shortDescription,
        type = appTypeMapper.toDomain(dto.type)
    )
}