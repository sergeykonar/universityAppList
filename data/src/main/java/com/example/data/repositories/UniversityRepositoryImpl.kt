package com.example.data.repositories

import com.example.data.di.UniversityApi
import com.example.domain.models.University
import com.example.domain.repositories.UniversityRepository
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(private val api: UniversityApi): UniversityRepository {
    override suspend fun getUniversities(country: String): University {
        return api.getUniversities(country)
    }

}
