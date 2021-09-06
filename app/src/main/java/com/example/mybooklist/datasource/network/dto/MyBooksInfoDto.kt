package com.example.mybooklist.datasource.network.dto

import com.example.mybooklist.datasource.database.entity.DatabaseBookEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MyBooksInfoDto(
    val meta: Meta,
    val documents: List<BookInfoDto>
)


fun MyBooksInfoDto.asDatabaseModel(): Array<DatabaseBookEntity> {
    return documents.map {
        DatabaseBookEntity(
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

// list to entity
