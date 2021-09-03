package com.example.mybooklist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybooklist.network.BookInfo

@Entity
data class DatabaseBook constructor(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
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
)

fun List<DatabaseBook>.asDomainModel():List<BookInfo>{
    return map{
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