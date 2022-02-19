package com.arya.movieapp.core.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

const val maxLinesCollapsed = 2
const val initialIsCollapsed = true
var isCollapsed = initialIsCollapsed

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.loadImage(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .transform(CenterCrop())
        .into(imageView)
}

fun TextView.collapsingTextView() {
    setOnClickListener {
        maxLines = if (isCollapsed) Int.MAX_VALUE else maxLinesCollapsed
        isCollapsed = !isCollapsed
    }
}