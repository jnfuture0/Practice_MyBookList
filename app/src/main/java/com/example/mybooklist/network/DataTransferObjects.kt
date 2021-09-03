package com.example.mybooklist.network

import com.example.mybooklist.database.DatabaseBook
import com.squareup.moshi.JsonClass


fun MyBooksInfo.asDomainModel(): List<BookInfo> {
    return documents.map {
        BookInfo(
            title = it.title,
            contents = it.contents,
            url = it.url,
            isbn = it.isbn,
            datetime = it.datetime,
            publisher = it.publisher,
            price = it.price,
            sale_price = it.sale_price,
            thumbnail = it.thumbnail,
            status = it.status
        )
    }
}

fun MyBooksInfo.asDatabaseModel(): Array<DatabaseBook>{
    return documents.map{
        DatabaseBook(
            title = it.title,
            contents = it.contents,
            url = it.url,
            isbn = it.isbn,
            datetime = it.datetime,
            publisher = it.publisher,
            price = it.price,
            sale_price = it.sale_price,
            thumbnail = it.thumbnail,
            status = it.status
        )
    }.toTypedArray()
}