package com.example.data.di

import com.example.domain.models.University
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApi {

    @GET("search")
    suspend fun getUniversities(@Query("country") country: String): University
}