package com.example.domain.usecases

import com.example.domain.repositories.UniversityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(private val universityRepository: UniversityRepository) {

    suspend operator fun invoke(country: String) = withContext(Dispatchers.IO){
        universityRepository.getUniversities(country)
    }
}