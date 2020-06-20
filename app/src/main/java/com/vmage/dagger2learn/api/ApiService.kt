package com.vmage.dagger2learn.api

import com.vmage.dagger2learn.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun queryCurrentYear(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("year") year: Int) : Call<Movie.MovieModel>
}