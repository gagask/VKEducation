package com.example.vkeducation.repository

import app.cash.turbine.test
import com.example.vkeducation.core.dispatchers.DispatcherProvider
import com.example.vkeducation.data.AppRepositoryImpl
import com.example.vkeducation.data.local.AppDetailsDao
import com.example.vkeducation.data.local.AppDetailsEntity
import com.example.vkeducation.data.local.AppDetailsEntityMapper
import com.example.vkeducation.data.remote.AppApi
import com.example.vkeducation.data.remote.AppDetailsDto
import com.example.vkeducation.data.remote.AppDetailsMapper
import com.example.vkeducation.data.remote.AppDto
import com.example.vkeducation.data.remote.AppMapper
import com.example.vkeducation.domain.App
import com.example.vkeducation.domain.AppDetails
import com.example.vkeducation.utils.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AppRepositoryImplUnitTest {

    private lateinit var repository: AppRepositoryImpl
    private lateinit var api: AppApi
    private lateinit var dao: AppDetailsDao
    private lateinit var appMapper: AppMapper
    private lateinit var appDetailsMapper: AppDetailsMapper
    private lateinit var entityMapper: AppDetailsEntityMapper
    private lateinit var dispatchers: DispatcherProvider

    @Before
    fun setUp() {
        api = mockk()
        dao = mockk()
        appMapper = mockk()
        appDetailsMapper = mockk()
        entityMapper = mockk()
        dispatchers = TestDispatcherProvider(Dispatchers.Unconfined)

        repository = AppRepositoryImpl(
            dispatchers = dispatchers,
            appMapper = appMapper,
            appDetailsMapper = appDetailsMapper,
            entityMapper = entityMapper,
            api = api,
            dao = dao
        )
    }


    @Test
    fun `getApps of emptyList returns emptyList`() = runTest{

        coEvery { api.getApps() } returns emptyList()


        val result = repository.getApps()


        assertEquals(emptyList<App>(), result)

        coVerify(exactly = 1) { api.getApps() }
        verify(inverse = true) { appMapper.toDomain(any()) }
    }

    @Test
    fun `getApps returns list correct`() = runTest{

        val appsDto = listOf(AppDto("1"), AppDto("2"))
        val apps = listOf(App("1"), App("2"))

        coEvery { api.getApps() } returns appsDto
        every { appMapper.toDomain(appsDto[0]) } returns apps[0]
        every { appMapper.toDomain(appsDto[1]) } returns apps[1]


        val result = repository.getApps()


        assertEquals(apps, result)

        coVerify(exactly = 1) { api.getApps() }
        verify(exactly = 1) { appMapper.toDomain(appsDto[0]) }
        verify(exactly = 1) { appMapper.toDomain(appsDto[1]) }
    }



    @Test
    fun `getAppDetails returns list from Dao`() = runTest{
        val id = "Nice test"

        val entity = AppDetailsEntity(id)
        val domain = AppDetails(id)
        val daoFlow = flowOf(entity)

        every { dao.getAppDetails(id) } returns daoFlow
        every { entityMapper.toDomain(entity) } returns domain


        val result = repository.getAppDetails(id)


        result.test {
            val first = awaitItem()

            assertEquals(domain, first)

            coVerify(inverse = true) { api.getAppDetails(any()) }
            verify(inverse = true) { appDetailsMapper.toDomain(any()) }
            coVerify(inverse = true) { dao.insertAppDetails(any())}
            verify(inverse = true) { entityMapper.toEntity(any())}
            cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) { dao.getAppDetails(id) }
        verify(exactly = 1) { entityMapper.toDomain(entity) }
    }


    @Test
    fun `getAppDetails returns list from Api`() = runTest{
        val id = "Nice test"

        val appDetailsDto = AppDetailsDto(id)
        val appDetailsDomain = AppDetails(id)
        val appDetailsEntity = AppDetailsEntity(id)

        every { dao.getAppDetails(id) } returns flowOf(null)
        coEvery { api.getAppDetails(id) } returns appDetailsDto
        every { appDetailsMapper.toDomain(appDetailsDto) } returns appDetailsDomain
        every { entityMapper.toEntity(appDetailsDomain) } returns appDetailsEntity
        every { dao.insertAppDetails(appDetailsEntity) } returns Unit


        val result = repository.getAppDetails(id)


        result.test {
            val first = awaitItem()

            assertEquals(appDetailsDomain, first)

            verify(inverse = true) { entityMapper.toDomain(any()) }

            cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) { dao.getAppDetails(id) }
        coVerify(exactly = 1) { api.getAppDetails(id) }
        verify(exactly = 1) { appDetailsMapper.toDomain(appDetailsDto) }
        verify(exactly = 1) { entityMapper.toEntity(appDetailsDomain) }
        verify(exactly = 1) { dao.insertAppDetails(appDetailsEntity) }
    }

    @Test
    fun `toggleWishlist call update with inversed flag`() = runTest{

        val id = "nice"

        val appDetailsEntity = AppDetailsEntity(id, isInWishlist = true)
        val entityFlow = flowOf(appDetailsEntity)

        coEvery { dao.getAppDetails(id) } returns entityFlow
        coEvery { dao.updateWishlistStatus(id, false) } returns Unit


        repository.toggleWishlist(id)


        coVerify(exactly = 1) { dao.getAppDetails(id) }
        coVerify(exactly = 1) { dao.updateWishlistStatus(id, false) }
    }


    @Test
    fun `toggleWishlist don't call update for null`() = runTest{

        val id = "nice"

        coEvery { dao.getAppDetails(id) } returns flowOf(null)


        repository.toggleWishlist(id)


        coVerify(exactly = 1) { dao.getAppDetails(id) }
        coVerify(inverse = true) { dao.updateWishlistStatus(any(), any()) }
    }


    @Test
    fun `observeAppDetails {null, 1, 2} to {1, 2}`() = runTest{

        val id = "nice"

        val entities = listOf(null, AppDetailsEntity(id), AppDetailsEntity(id, isInWishlist = true))
        val entitiesFlow = flowOf(entities[0], entities[1], entities[2])
        val domain = listOf(AppDetails(id), AppDetails(id, isInWishlist = true))

        coEvery { dao.getAppDetails(id) } returns entitiesFlow
        every { entityMapper.toDomain(entities[1]!!) } returns domain[0]
        every { entityMapper.toDomain(entities[2]!!) } returns domain[1]

        val result = repository.observeAppDetails(id)

        result.test {
            val first = awaitItem()
            assertEquals(domain[0], first)

            val second = awaitItem()
            assertEquals(domain[1], second)

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { dao.getAppDetails(id) }
        verify(exactly = 1) { entityMapper.toDomain(entities[1]!!) }
        verify(exactly = 1) { entityMapper.toDomain(entities[2]!!) }
    }
}