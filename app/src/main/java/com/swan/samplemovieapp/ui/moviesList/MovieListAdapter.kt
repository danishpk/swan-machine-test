package com.swan.samplemovieapp.ui.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.swan.samplemovieapp.R
import com.swan.samplemovieapp.data.models.Movie
import com.swan.samplemovieapp.databinding.MovieListItemBinding

class MoviesListAdapter(private val onItemClick: (movie: Movie) -> Unit) :
    PagingDataAdapter<Movie, MoviesListAdapter.MovieListViewHolder>(DIFF_CALLBACK) {

    inner class MovieListViewHolder(binding: MovieListItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val itemBinding = binding

        fun bind (movie: Movie, onItemClick: (movie: Movie) -> Unit) {
            itemBinding.apply {
                tvMovieTitle.text = movie.title
                tvMovieReleaseDate.text = movie.releaseDate

                Glide.with(itemView)
                    .load(movie.imageUrl)
                    .centerCrop()
                    .placeholder(
                        ContextCompat.getDrawable(itemView.context,
                            R.drawable.img_placeholder))
                    .error(R.drawable.img_placeholder)
                    // .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivMoviePoster)

                itemView.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val itemBinding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClick) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}