package com.example.vkeducation.data

import com.example.vkeducation.data.local.AppDetailsDao
import com.example.vkeducation.data.local.AppDetailsEntityMapper
import com.example.vkeducation.domain.App
import com.example.vkeducation.domain.AppDetails
import com.example.vkeducation.domain.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    val appMapper: AppMapper,
    val appDetailsMapper: AppDetailsMapper,
    val entityMapper: AppDetailsEntityMapper,
    val api: AppApi,
    val dao: AppDetailsDao,
): AppRepository {

    override suspend fun getApps(): List<App> {
        val dto = api.getApps()
        return dto.map{ appMapper.toDomain(it) }
    }

    override suspend fun getAppDetails(id: String): AppDetails {
        val appDetailsEntity = dao.getAppDetails(id)
        if (appDetailsEntity != null)
            return entityMapper.toDomain(appDetailsEntity)

        val dto = api.getAppDetails(id)
        val domain = appDetailsMapper.toDomain(dto)
        withContext(Dispatchers.IO){
            dao.insertAppDetails(entityMapper.toEntity(domain))
        }
        return domain
    }
}