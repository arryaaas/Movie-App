package com.arya.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvResponse (

    @field:SerializedName("results")
    val results: List<TvResponse>,

)