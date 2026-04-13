package com.example.vkeducation.mappers

import com.example.vkeducation.data.remote.AppDetailsMapper
import com.example.vkeducation.data.remote.AppDetailsDto
import com.example.vkeducation.domain.AppDetails
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AppDetailsMapperUnitTest {

    @Test
    fun `map dto to domain is correct`() {
        val dto = AppDetailsDto(
            id = "1",
            name = "2",
            developer = "3",
            category = "4",
            ageRating = 5,
            size = 6f,
            iconUrl = "7",
            screenshotUrlList = emptyList<String>(),
            description = "9"
        )

        val domain = AppDetailsMapper().toDomain(dto)

        assertEquals(
            AppDetails(
                id = "1",
                name = "2",
                developer = "3",
                category = "4",
                ageRating = 5,
                size = 6f,
                iconUrl = "7",
                screenshotUrlList = emptyList<String>(),
                description = "9",
                isInWishlist = false
            )
            ,
            domain
        )
    }
}