package com.arya.movieapp.detail

import androidx.lifecycle.ViewModel
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavorite(movie: Movie, newState: Boolean) {
        movieUseCase.setFavorite(movie, newState)
    }

}