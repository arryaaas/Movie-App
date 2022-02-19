package com.arya.movieapp.core.domain.usecase

import com.arya.movieapp.core.data.source.Resource
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie(sort: String): Flow<Resource<List<Movie>>> {
        return iMovieRepository.getAllMovie(sort)
    }

    override fun getAllTv(sort: String): Flow<Resource<List<Movie>>> {
        return iMovieRepository.getAllTv(sort)
    }

    override fun getAllFavoriteMovie(): Flow<List<Movie>> {
        return iMovieRepository.getAllFavoriteMovie()
    }

    override fun getAllFavoriteTv(): Flow<List<Movie>> {
        return iMovieRepository.getAllFavoriteTv()
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
        return iMovieRepository.setFavorite(movie, state)
    }

    override fun getSearchData(title: String): Flow<List<Movie>> {
        return iMovieRepository.getSearchData(title)
    }

}