package com.example.vkeducation.data

import com.example.vkeducation.domain.AppType

class AppTypeMapper {
    fun toDomain(appType: String) = when(appType) {
        "Tools" -> AppType.TOOLS
        "Finance" -> AppType.FINANCE
        "Transport" -> AppType.TRANSPORT
        else -> throw IllegalStateException("Unsupported app type: $appType")
    }
}