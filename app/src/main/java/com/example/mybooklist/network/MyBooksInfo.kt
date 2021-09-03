package com.example.mybooklist.network

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class MyBooksInfo(
    val meta:Meta,
    val documents:List<BookInfo>
)

data class Meta(
    val total_count:Int,
    val pageable_count:Int,
    val is_end : Boolean
)

@JsonClass(generateAdapter = true)
data class BookInfo(
    val title:String,
    val contents:String,
    val url:String,
    val isbn:String,
    val datetime:String,
    val publisher:String,
    val price:Int,
    val sale_price:Int,
    val thumbnail:String,
    val status:String
) :Serializable