package com.vmage.dagger2learn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vmage.dagger2learn.paging.MovieDataSourceFactory

class MainViewModel : ViewModel() {

    private val config by lazy {
        PagedList.Config.Builder()
            .setMaxSize(20)
            .setInitialLoadSizeHint(40)
            .build()
    }

    private val movieFactoryFactory by lazy {
        MovieDataSourceFactory()
    }

    val pagedList = LivePagedListBuilder(movieFactoryFactory, config).build()
}