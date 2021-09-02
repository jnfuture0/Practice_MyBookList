package com.example.mybooklist.network

import com.example.mybooklist.database.DatabaseBook
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
//data class NetworkBooksContainer(val books:MyBooksInfo)
//
//@JsonClass(generateAdapter = true)
//data class NetworkBooks(
//    val title:String,
//    val contents:String,
//    val url:String,
//    val isbn:String,
//    val datetime:String,
//    val authors:List<String>,
//    val publisher:String,
//    val translators:List<String>,
//    val price:Int,
//    val sale_price:Int,
//    val thumbnail:String,
//    val status:String
//    )

//fun NetworkBooksContainer.asDomainModel(): List<BookInfo> {
//    return books.map {
//        BookInfo(
//            title = it.title,
//            contents = it.contents,
//            url = it.url,
//            isbn = it.isbn,
//            datetime = it.datetime,
//            authors = it.authors,
//            publisher = it.publisher,
//            translators = it.translators,
//            price = it.price,
//            sale_price = it.sale_price,
//            thumbnail = it.thumbnail,
//            status = it.status
//        )
//    }
//}

fun MyBooksInfo.asDatabaseModel(): Array<DatabaseBook>{
    return documents.map{
        DatabaseBook(
            title = it.title,
            contents = it.contents,
            url = it.url,
            isbn = it.isbn,
            datetime = it.datetime,
            authors = it.authors,
            publisher = it.publisher,
            translators = it.translators,
            price = it.price,
            sale_price = it.sale_price,
            thumbnail = it.thumbnail,
            status = it.status
        )
    }.toTypedArray()
}