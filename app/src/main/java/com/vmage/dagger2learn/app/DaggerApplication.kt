package com.vmage.dagger2learn.app

import android.app.Application
import com.vmage.dagger2learn.component.DaggerMovieComponent
import com.vmage.dagger2learn.component.MovieComponent


class DaggerApplication : Application() {

    companion object {
        lateinit var movieComponent: MovieComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        movieComponent = DaggerMovieComponent
            .builder()
            .build()
    }
}