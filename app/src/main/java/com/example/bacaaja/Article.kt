package com.example.bacaaja

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val description: String,
    val photo: String,
    val content: String,
    val like: Int,
    val bookmark: Int,
    val readTime: String,
    val authorName: String,
    val authorJob: String,
    val authorPicture: String,
) : Parcelable
