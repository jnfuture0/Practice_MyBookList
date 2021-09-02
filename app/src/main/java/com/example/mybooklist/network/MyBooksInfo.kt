package com.example.mybooklist.network

import java.io.Serializable


data class MyBooksInfo(
    val meta:Meta,
    val documents:List<BookInfo>
)
//    : Parcelable{
//    val isEnd
//        get() = meta.is_end
//}

data class Meta(
    val total_count:Int,
    val pageable_count:Int,
    val is_end : Boolean
)


data class BookInfo(
    val title:String,
    val contents:String,
    val url:String,
    val isbn:String,
    val datetime:String,
    val authors:List<String>,
    val publisher:String,
    val translators:List<String>,
    val price:Int,
    val sale_price:Int,
    val thumbnail:String,
    val status:String
) :Serializable