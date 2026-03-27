package com.example.vkeducation.data

import com.example.vkeducation.domain.App
import com.example.vkeducation.domain.AppDetails
import com.example.vkeducation.domain.AppRepository
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    val appMapper: AppMapper,
    val appDetailsMapper: AppDetailsMapper,
    val api: AppApi
): AppRepository {

    override suspend fun getApps(): List<App> {
        val dto = api.getApps()
        return dto.map{ appMapper.toDomain(it) }
    }

    override suspend fun getApp(id: String): AppDetails {
        val dto = api.getApp(id)
        return appDetailsMapper.toDomain(dto)
    }
}