package com.arya.movieapp.core.utils

import com.arya.movieapp.core.data.source.local.entity.MovieEntity
import com.arya.movieapp.core.data.source.remote.response.MovieResponse
import com.arya.movieapp.core.data.source.remote.response.TvResponse
import com.arya.movieapp.core.domain.model.Movie

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = false,
                isMovie = true,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvResponsesToEntities(input: List<TvResponse>): List<MovieEntity> {
        val tvList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.name,
                overview = it.overview,
                releaseDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = false,
                isMovie = false
            )
            tvList.add(movie)
        }
        return tvList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id ?: 0,
                title = it.title.toString(),
                overview = it.overview.toString(),
                releaseDate = it.releaseDate.toString(),
                voteAverage = it.voteAverage ?: 0F,
                popularity = it.popularity ?: 0F,
                originalLanguage = it.originalLanguage.toString(),
                posterPath = it.posterPath.toString(),
                backdropPath = it.backdropPath.toString(),
                isFavorite = it.isFavorite,
                isMovie = it.isMovie
            )
        }

    fun mapDomainToEntities(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            popularity = input.popularity,
            originalLanguage = input.originalLanguage,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            isFavorite = input.isFavorite,
            isMovie = input.isMovie
        )
    }

}