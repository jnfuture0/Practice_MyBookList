package com.example.mybooklist.di

import android.content.Context
import androidx.room.Room
import com.example.mybooklist.datasource.database.BooksDatabase
import com.example.mybooklist.datasource.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideBookDao(db: BooksDatabase): BookDao {
        return db.bookDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BooksDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            BooksDatabase::class.java,
            "books"
        ).fallbackToDestructiveMigration().build()
    }

}