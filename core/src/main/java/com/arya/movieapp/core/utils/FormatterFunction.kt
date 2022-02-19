package com.arya.movieapp.core.utils

import com.arya.movieapp.core.BuildConfig.SITE_URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun formatToolbarTitle(isMovie: Boolean): String = if (isMovie) {
    "Movie Detail"
} else {
    "Tv Show Detail"
}

fun formatDate(releaseDate: String, format: String): String {
    var formattedDate = ""
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    try {
        sdf.parse(releaseDate)?.let {
            formattedDate = SimpleDateFormat(format, Locale.US).format(it)
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}

fun formatOverview(overview: String): String = overview.ifEmpty {
    "Sorry, the synopsis is not yet available."
}

fun formatLanguage(originalLanguage: String): String =
    Locale(originalLanguage).getDisplayLanguage(Locale.US)

fun formatSiteUrl(isMovie: Boolean, id: Int): String = if (isMovie) {
    "$SITE_URL/movie/$id"
} else {
    "$SITE_URL/tv/$id"
}