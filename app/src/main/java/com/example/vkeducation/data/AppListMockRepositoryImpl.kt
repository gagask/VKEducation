package com.example.vkeducation.data

import com.example.vkeducation.domain.AppCard
import com.example.vkeducation.domain.AppListRepository

class AppListMockRepositoryImpl(
    private val mapper: AppCardMapper,
    private val api: AppListApi
): AppListRepository {
    override suspend fun get(): List<AppCard> {
        val dto = api.get()
        return dto.map{ mapper.toDomain(it) }
    }

}