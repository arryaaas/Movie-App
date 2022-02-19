package com.arya.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    @field:SerializedName("popularity")
    val popularity: Float,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

)