package com.vmage.dagger2learn.paging

import androidx.paging.DataSource
import com.vmage.dagger2learn.model.Movie

class MovieDataSourceFactory: DataSource.Factory<Int, Movie.MovieData>() {
    override fun create(): DataSource<Int, Movie.MovieData> {
        return MovieDataSource()
    }
}