package com.example.vkeducation.data

import com.example.vkeducation.domain.AppDetails
import com.example.vkeducation.domain.AppDetailsRepository
import javax.inject.Inject

class AppDetailsMockRepositoryImpl @Inject constructor(
    val mapper: AppDetailsMapper,
    val api: AppDetailsApi
): AppDetailsRepository {
    override suspend fun get(id: String): AppDetails {
        val dto = api.get(id)
        return mapper.toDomain(dto)
    }
}