package com.arya.movieapp.core.data.source.local

import com.arya.movieapp.core.data.source.local.entity.MovieEntity
import com.arya.movieapp.core.data.source.local.room.MovieDao
import com.arya.movieapp.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val mMovieDao: MovieDao) {

    fun getAllMovie(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryMovie(sort)
        return mMovieDao.getAllMovie(query)
    }

    fun getAllTv(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryTv(sort)
        return mMovieDao.getAllTv(query)
    }

    fun getAllFavoriteMovie(): Flow<List<MovieEntity>> {
        return mMovieDao.getAllFavoriteMovie()
    }

    fun getAllFavoriteTv(): Flow<List<MovieEntity>> {
        return mMovieDao.getAllFavoriteTv()
    }

    fun setFavorite(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieDao.updateData(movie)
    }

    fun getSearchData(title: String): Flow<List<MovieEntity>> {
        return mMovieDao.getSearchData(title)
    }

    suspend fun insertData(movie: List<MovieEntity>) = mMovieDao.insertData(movie)

}