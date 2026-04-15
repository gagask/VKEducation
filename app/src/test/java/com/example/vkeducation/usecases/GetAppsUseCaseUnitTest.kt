package com.example.vkeducation.usecases

import com.example.vkeducation.domain.App
import com.example.vkeducation.domain.AppRepository
import com.example.vkeducation.domain.GetAppsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAppsUseCaseUnitTest {

    private lateinit var useCase: GetAppsUseCase
    private lateinit var repository: AppRepository

    @Before
    fun setUp() {

        repository = mockk()
        useCase = GetAppsUseCase(repository)
    }

    @Test
    fun `invoke should return apps from repository`() = runTest {
        val apps = listOf(App(id = "1"), App(id = "2"))

        coEvery { repository.getApps() } returns apps


        val result = useCase()


        assertEquals(apps, result)
        coVerify(exactly = 1) { repository.getApps() }
    }

}