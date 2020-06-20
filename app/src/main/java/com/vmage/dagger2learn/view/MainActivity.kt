package com.vmage.dagger2learn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.vmage.dagger2learn.R
import com.vmage.dagger2learn.component.DaggerMovieComponent
import com.vmage.dagger2learn.model.Movie
import com.vmage.dagger2learn.repository.MovieRepository
import com.vmage.dagger2learn.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val adapter by lazy {
        MovieAdapter(Glide.with(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setUpObserver()
    }

    private fun initView() {
        val layoutManager = GridLayoutManager(this, 2)
        main_recycler_view.layoutManager = layoutManager
        main_recycler_view.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
