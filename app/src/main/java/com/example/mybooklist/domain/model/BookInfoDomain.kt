package com.example.mybooklist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookInfoDomain(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val publisher: String,
    val price: Int,
    val sale_price: Int,
    val thumbnail: String,
    val status: String
): Parcelable