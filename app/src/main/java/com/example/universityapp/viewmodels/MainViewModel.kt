package com.example.universityapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Resource
import com.example.domain.models.University
import com.example.domain.models.UniversityItem
import com.example.domain.usecases.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetUniversitiesUseCase): ViewModel() {

    var data: MutableLiveData<Resource<Serializable>> = MutableLiveData()


    suspend fun getUni(country: String) = flow{

        data.value = Resource.Loading
        emit(Resource.Loading)
        try {
            val res = Resource.Data(useCase(country))
            data.value = res
            emit(res)
        }catch (e: Exception){
            data.value = Resource.Error(e)
            emit(Resource.Error(e))
        }


    }

    fun observeData(): MutableLiveData<Resource<Serializable>>{
        return data
    }
}