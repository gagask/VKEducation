package com.example.vkeducation.data

import com.example.vkeducation.data.dto.AppDetailsDto
import com.example.vkeducation.data.dto.AppDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("/catalog")
    suspend fun getApps(): List<AppDto>

    @GET("/catalog/{id}")
    suspend fun getApp(
        @Path("id") id: String
    ): AppDetailsDto
}