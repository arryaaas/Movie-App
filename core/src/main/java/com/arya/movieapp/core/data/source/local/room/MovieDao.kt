package com.arya.movieapp.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.arya.movieapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovie(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllTv(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(movies: List<MovieEntity>)

    @Update
    fun updateData(movies: MovieEntity)

    @Query("SELECT * FROM movie WHERE isMovie = 1 AND isFavorite = 1")
    fun getAllFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isMovie = 0 AND isFavorite = 1")
    fun getAllFavoriteTv(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun getSearchData(title: String): Flow<List<MovieEntity>>
}