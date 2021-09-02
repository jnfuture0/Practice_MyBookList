package com.example.mybooklist.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mybooklist.util.Converters

@Dao
interface BookDao{
    @Query("SELECT * FROM databasebook")
    fun getBooks():LiveData<List<DatabaseBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg books:DatabaseBook)
}

@TypeConverters(Converters::class)
@Database(entities = [DatabaseBook::class], version = 1)
abstract class BooksDatabase:RoomDatabase(){
    abstract val bookDao:BookDao
}

private lateinit var INSTANCE:BooksDatabase

fun getDatabase(context: Context):BooksDatabase{
    synchronized(BooksDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BooksDatabase::class.java,
                "books"
            ).build()
        }
        return INSTANCE
    }
}