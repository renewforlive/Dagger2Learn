package com.vmage.dagger2learn.paging

import androidx.paging.PageKeyedDataSource
import com.vmage.dagger2learn.BuildConfig
import com.vmage.dagger2learn.api.ApiService
import com.vmage.dagger2learn.app.DaggerApplication
import com.vmage.dagger2learn.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieDataSource: PageKeyedDataSource<Int, Movie.MovieData>() {

    @Inject lateinit var apiService: ApiService

    companion object {
        private const val YEAR = 2019
    }

    init {
        DaggerApplication.movieComponent.inject(this)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie.MovieData>
    ) {
        val call = apiService.queryCurrentYear(BuildConfig.API_KEY, 1, YEAR)
        call.enqueue(object : Callback<Movie.MovieModel> {
            override fun onFailure(call: Call<Movie.MovieModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Movie.MovieModel>,
                response: Response<Movie.MovieModel>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let {
                        callback.onResult(it.results, null, 2)
                    }
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie.MovieData>) {
        val call = apiService.queryCurrentYear(BuildConfig.API_KEY, params.key, YEAR)
        call.enqueue(object : Callback<Movie.MovieModel> {
            override fun onFailure(call: Call<Movie.MovieModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<Movie.MovieModel>, response: Response<Movie.MovieModel>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let {
                        callback.onResult(it.results, params.key + 1)
                    }
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie.MovieData>) {
        TODO("Not yet implemented")
    }
}