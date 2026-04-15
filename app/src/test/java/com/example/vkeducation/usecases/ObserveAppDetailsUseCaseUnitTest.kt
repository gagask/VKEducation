package com.example.vkeducation.usecases

import app.cash.turbine.test
import com.example.vkeducation.domain.AppDetails
import com.example.vkeducation.domain.AppRepository
import com.example.vkeducation.domain.ObserveAppDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ObserveAppDetailsUseCaseUnitTest {

    private lateinit var useCase: ObserveAppDetailsUseCase
    private lateinit var repository: AppRepository

    @Before
    fun setUp() {

        repository = mockk()
        useCase = ObserveAppDetailsUseCase(repository)
    }

    @Test
    fun `invoke should return appDetails from repository`() = runTest {

        val id = "nice"

        val domain = AppDetails(id)

        coEvery { repository.observeAppDetails(id) } returns flowOf(domain)


        val result = useCase(id)

        result.test{
            val first = awaitItem()

            assertEquals(domain, first)

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { repository.observeAppDetails(id) }
    }

}