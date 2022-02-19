package com.arya.movieapp.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val TITLE = "title"
    const val RELEASE_DATE = "release date"
    const val RATING = "rating"

    fun getSortedQueryMovie(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie WHERE isMovie = 1 ")
        when (filter) {
            TITLE -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RELEASE_DATE -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            RATING -> {
                simpleQuery.append("ORDER BY voteAverage DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTv(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie WHERE isMovie = 0 ")
        when (filter) {
            TITLE -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RELEASE_DATE -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            RATING -> {
                simpleQuery.append("ORDER BY voteAverage DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}