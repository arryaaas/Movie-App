package com.arya.movieapp.core.domain.repository

import com.arya.movieapp.core.data.source.Resource
import com.arya.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(sort: String): Flow<Resource<List<Movie>>>

    fun getAllTv(sort: String): Flow<Resource<List<Movie>>>

    fun getAllFavoriteMovie(): Flow<List<Movie>>

    fun getAllFavoriteTv(): Flow<List<Movie>>

    fun setFavorite(movie: Movie, state: Boolean)

    fun getSearchData(title: String): Flow<List<Movie>>

}