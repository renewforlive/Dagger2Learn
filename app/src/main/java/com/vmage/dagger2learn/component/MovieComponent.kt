package com.vmage.dagger2learn.component

import com.vmage.dagger2learn.module.ApiModule
import com.vmage.dagger2learn.paging.MovieDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface MovieComponent {
    fun inject(movieDataSource: MovieDataSource)
}