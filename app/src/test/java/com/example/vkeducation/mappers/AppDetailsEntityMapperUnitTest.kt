package com.example.vkeducation.mappers

import com.example.vkeducation.data.local.AppDetailsEntity
import com.example.vkeducation.data.local.AppDetailsEntityMapper
import com.example.vkeducation.domain.AppDetails
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AppDetailsEntityMapperUnitTest {

    @Test
    fun `map entity to domain is correct`() {
        val entity = AppDetailsEntity()

        val domain = AppDetailsEntityMapper().toDomain(entity)

        assertEquals(AppDetails(), domain)
    }

    @Test
    fun `map domain to entity is correct`() {
        val domain = AppDetails()

        val entity = AppDetailsEntityMapper().toEntity(domain)

        assertEquals(AppDetailsEntity(), entity)
    }
}