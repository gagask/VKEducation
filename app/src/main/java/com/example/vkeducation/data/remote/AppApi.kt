package com.example.vkeducation.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("/catalog")
    suspend fun getApps(): List<AppDto>

    @GET("/catalog/{id}")
    suspend fun getAppDetails(
        @Path("id") id: String
    ): AppDetailsDto
}