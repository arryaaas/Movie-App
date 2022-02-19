package com.arya.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Float,
    val popularity: Float,
    val originalLanguage: String,
    val posterPath: String,
    val backdropPath: String,
    var isFavorite: Boolean,
    val isMovie: Boolean
) : Parcelable