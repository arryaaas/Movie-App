package com.arya.movieapp.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arya.movieapp.core.data.source.Resource
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.usecase.MovieUseCase

class TvViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getAllTv(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllTv(sort).asLiveData()

}