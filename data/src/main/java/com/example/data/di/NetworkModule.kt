package com.example.data.di

import com.example.data.repositories.UniversityRepositoryImpl
import com.example.domain.repositories.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val baseUrl = "http://universities.hipolabs.com/"

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit.Builder): UniversityApi{
        return retrofit.build().create(UniversityApi::class.java)
    }

    @Singleton
    @Provides
    fun providesUniversityRepository(universityRepository: UniversityRepositoryImpl): UniversityRepository {
        return universityRepository
    }

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}