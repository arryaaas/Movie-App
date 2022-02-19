package com.arya.movieapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.usecase.MovieUseCase

class FavoriteItemViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getAllFavoriteMovie(): LiveData<List<Movie>> =
        movieUseCase.getAllFavoriteMovie().asLiveData()

    fun getAllFavoriteTv(): LiveData<List<Movie>> =
        movieUseCase.getAllFavoriteTv().asLiveData()

}