package com.example.vkeducation.usecases

import com.example.vkeducation.domain.AppRepository
import com.example.vkeducation.domain.ToggleWishlistUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleWishlistUseCaseUnitTest {

    private lateinit var useCase: ToggleWishlistUseCase
    private lateinit var repository: AppRepository

    @Before
    fun setUp() {

        repository = mockk()
        useCase = ToggleWishlistUseCase(repository)
    }

    @Test
    fun `invoke should return appDetails from repository`() = runTest {

        val id = "nice"

        coEvery { repository.toggleWishlist(id) } returns Unit


        useCase(id)


        coVerify(exactly = 1) { repository.toggleWishlist(id) }
    }

}