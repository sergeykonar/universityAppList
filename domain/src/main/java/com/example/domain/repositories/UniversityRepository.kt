package com.example.domain.repositories

import com.example.domain.models.University

interface UniversityRepository {
    suspend fun getUniversities(country: String): University
}