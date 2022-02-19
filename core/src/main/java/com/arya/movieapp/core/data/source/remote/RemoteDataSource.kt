package com.arya.movieapp.core.data.source.remote

import android.util.Log
import com.arya.movieapp.core.BuildConfig
import com.arya.movieapp.core.data.source.remote.network.ApiService
import com.arya.movieapp.core.data.source.remote.response.MovieResponse
import com.arya.movieapp.core.data.source.remote.response.TvResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val apiKey = BuildConfig.API_KEY

    suspend fun getMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "getMovie: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTv(): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getTv(apiKey)
                val tvList = response.results
                if (tvList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "getTv: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}