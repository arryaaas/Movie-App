package com.arya.movieapp.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arya.movieapp.core.data.source.Resource
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getAllMovie(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllMovie(sort).asLiveData()

}