package com.arya.movieapp.di

import com.arya.movieapp.core.domain.usecase.MovieInteractor
import com.arya.movieapp.core.domain.usecase.MovieUseCase
import com.arya.movieapp.detail.DetailViewModel
import com.arya.movieapp.movie.MovieViewModel
import com.arya.movieapp.search.SearchViewModel
import com.arya.movieapp.tv.TvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}