package com.arya.movieapp.core.data.source.remote.network

import com.arya.movieapp.core.BuildConfig
import com.arya.movieapp.core.data.source.remote.response.ListMovieResponse
import com.arya.movieapp.core.data.source.remote.response.ListTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ListMovieResponse

    @GET("/3/discover/tv")
    suspend fun getTv(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ListTvResponse

}