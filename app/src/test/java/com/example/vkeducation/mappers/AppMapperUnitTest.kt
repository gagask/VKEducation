package com.example.vkeducation.mappers

import com.example.vkeducation.data.remote.AppMapper
import com.example.vkeducation.data.remote.AppDto
import com.example.vkeducation.domain.App
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AppMapperUnitTest {
    @Test
    fun `map dto to domain is correct`() {
        val dto = AppDto(
            id = "ID",
            name = "NAME",
            description = "DESCRIPTION",
            category = "CATEGORY",
            iconUrl = "ICON_URL"
        )

        val domain = AppMapper().toDomain(dto)

        assertEquals(
            App(
                id = "ID",
                name = "NAME",
                description = "DESCRIPTION",
                category = "CATEGORY",
                iconUrl = "ICON_URL"
            ),
            domain
        )
    }
}