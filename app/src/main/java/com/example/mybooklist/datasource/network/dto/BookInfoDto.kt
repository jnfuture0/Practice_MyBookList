package com.example.mybooklist.datasource.network.dto

import com.example.mybooklist.datasource.database.entity.DatabaseBookEntity
import com.example.mybooklist.domain.model.BookInfoDomain
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class BookInfoDto(
    val title: String,  // abcd
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val publisher: String,
    val price: Int,
    val sale_price: Int,
    val thumbnail: String,
    val status: String
)

// Serializable --> Parcelable

fun BookInfoDto.asDomainModel(): BookInfoDomain {
    return BookInfoDomain(
        title = title/*.first().uppercase()*/, // Abcde
        contents = contents,
        url = url,
        isbn = isbn,
        datetime = datetime,
        publisher = publisher,
        price = price,
        sale_price = sale_price,
        thumbnail = thumbnail,
        status = status
    )
}

fun List<BookInfoDto>.asDomainList(): List<BookInfoDomain>{
    return map{
        it.asDomainModel()
    }
}

fun BookInfoDto.asDatabaseModel(): DatabaseBookEntity{
    return DatabaseBookEntity(
        title = title,
        contents = contents,
        url = url,
        isbn = isbn,
        datetime = datetime,
        publisher = publisher,
        price = price,
        sale_price = sale_price,
        thumbnail = thumbnail,
        status = status
    )
}