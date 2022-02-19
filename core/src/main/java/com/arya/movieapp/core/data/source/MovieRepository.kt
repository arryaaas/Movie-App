package com.arya.movieapp.core.data.source

import com.arya.movieapp.core.data.source.local.LocalDataSource
import com.arya.movieapp.core.data.source.remote.ApiResponse
import com.arya.movieapp.core.data.source.remote.RemoteDataSource
import com.arya.movieapp.core.data.source.remote.response.MovieResponse
import com.arya.movieapp.core.data.source.remote.response.TvResponse
import com.arya.movieapp.core.domain.model.Movie
import com.arya.movieapp.core.domain.repository.IMovieRepository
import com.arya.movieapp.core.utils.AppExecutors
import com.arya.movieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertData(movieList)
            }
        }.asLiveData()

    override fun getAllTv(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllTv(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }


            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getTv()
            }

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val movieList = DataMapper.mapTvResponsesToEntities(data)
                localDataSource.insertData(movieList)
            }
        }.asLiveData()

    override fun getAllFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getAllFavoriteTv(): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteTv().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavorite(movieEntity, state)
        }
    }

    override fun getSearchData(title: String): Flow<List<Movie>> {
        return localDataSource.getSearchData(title).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

}