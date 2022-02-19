package com.arya.movieapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.usecase.MovieUseCase

class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getSearchData(title: String) : LiveData<List<Movie>> =
        movieUseCase.getSearchData(title).asLiveData()

}