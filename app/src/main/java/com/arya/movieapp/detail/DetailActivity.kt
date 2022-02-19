package com.arya.movieapp.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.arya.movieapp.R
import com.arya.movieapp.core.BuildConfig.*
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.utils.*
import com.arya.movieapp.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val movie = args.movie
        populateDetail(movie)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateDetail(movie: Movie) {
        binding.apply {
            collapsingToolbar.title = formatToolbarTitle(movie.isMovie)

            this@DetailActivity.loadImage(URL_BACKDROP + movie.backdropPath, ivBackdrop)
            this@DetailActivity.loadImage(URL_POSTER + movie.posterPath, ivPoster)

            tvTitle.text = movie.title
            tvRelease.text = formatDate(movie.releaseDate, "MMMM dd, yyyy")
            ratingBar.rating = formatVoteAverage(movie.voteAverage).toFloat()
            tvRating.text = formatVoteAverage(movie.voteAverage)

            tvOverview.text = formatOverview(movie.overview)
            tvOverview.collapsingTextView()

            tvPopularity.text = movie.popularity.toString()
            tvLanguage.text = formatLanguage(movie.originalLanguage)

            var favoriteState = movie.isFavorite
            setFavorite(favoriteState)
            fabFavorite.setOnClickListener {
                favoriteState = !favoriteState
                viewModel.setFavorite(movie, favoriteState)
                setFavorite(favoriteState)
            }

            btnShare.setOnClickListener {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, formatSiteUrl(movie.isMovie, movie.id))
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        binding.apply {
            if (state) {
                fabFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailActivity, R.drawable.ic_favorite
                    )
                )
            } else {
                fabFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailActivity, R.drawable.ic_favorite_border
                    )
                )
            }
        }
    }

}