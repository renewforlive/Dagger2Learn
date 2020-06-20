package com.vmage.dagger2learn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.vmage.dagger2learn.R
import com.vmage.dagger2learn.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val glide: RequestManager) : PagedListAdapter<Movie.MovieData, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Movie.MovieData>() {
            override fun areItemsTheSame(
                oldItem: Movie.MovieData,
                newItem: Movie.MovieData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie.MovieData,
                newItem: Movie.MovieData
            ): Boolean {
                return oldItem == newItem
            }
        }

        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                getItem(position)?.let {
                    holder.bind(it)
                }
            }
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Movie.MovieData) {
            val postUrl = IMAGE_BASE_URL + model.poster_path
            glide.load(postUrl).into(itemView.movie_image)
            itemView.movie_title.text = model.title
        }
    }
}