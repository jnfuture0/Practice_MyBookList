package com.example.mybooklist.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mybooklist.datasource.database.entity.DatabaseBookEntity

@Dao
interface BookDao {
    @Query("SELECT * FROM databasebookentity")
    fun getBooks(): List<DatabaseBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg books: DatabaseBookEntity)

    @Query("DELETE from databasebookentity")
    fun deleteAll()
}