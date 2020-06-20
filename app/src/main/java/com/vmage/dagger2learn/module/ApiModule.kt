package com.vmage.dagger2learn.module

import com.vmage.dagger2learn.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @Provides
    @Singleton
    fun getApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}