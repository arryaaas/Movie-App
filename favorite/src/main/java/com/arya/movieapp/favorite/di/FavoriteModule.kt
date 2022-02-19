package com.arya.movieapp.favorite.di

import com.arya.movieapp.favorite.FavoriteItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteItemViewModel(get()) }
}