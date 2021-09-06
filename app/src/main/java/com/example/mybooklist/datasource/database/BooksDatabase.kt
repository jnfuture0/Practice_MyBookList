package com.example.mybooklist.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mybooklist.datasource.database.dao.BookDao
import com.example.mybooklist.datasource.database.entity.DatabaseBookEntity

@Database(entities = [DatabaseBookEntity::class], version = 4)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    //abstract val bookDao: BookDao
}