package com.example.mybooklist.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybooklist.domain.model.BookInfoDomain

@Entity
data class DatabaseBookEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val publisher: String,
    val price: Int,
    val sale_price: Int,
    val thumbnail: String,
    val status: String,

    //  val jeongRo: String
)

fun DatabaseBookEntity.asDomainModel(): BookInfoDomain {
    return BookInfoDomain(
        //id = id,
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

fun List<DatabaseBookEntity>.asDomainModel(): List<BookInfoDomain> {
    return map {
        it.asDomainModel()
    }
}

//data class JeongRoDomain(
//    val name: String,
//    val age: Int
//)
//fun JeongRoDomain.toDbModel(): DatabaseBook{
//    return DatabaseBook(
//        jeongRo = this.name + "," + this.age
//    )
//}
//fun toDomain(){
//    return JeongRoDomain(
//        name = this.split(",").first()
//    age = second()
//    )
//}

