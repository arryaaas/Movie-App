package com.arya.movieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arya.movieapp.core.BuildConfig.URL_POSTER
import com.arya.movieapp.core.databinding.ItemListBinding
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.utils.formatDate
import com.arya.movieapp.core.utils.formatVoteAverage
import com.arya.movieapp.core.utils.loadImage

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): MovieViewHolder {
        val itemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                itemView.context.loadImage(URL_POSTER + movie.posterPath, ivPoster)

                tvTitle.text = movie.title
                tvRelease.text = formatDate(movie.releaseDate, "MMMM dd, yyyy")
                ratingBar.rating = formatVoteAverage(movie.voteAverage).toFloat()
                tvRating.text = formatVoteAverage(movie.voteAverage)

                itemView.setOnClickListener {
                    onItemClick?.invoke(movie)
                }
            }
        }

    }

}