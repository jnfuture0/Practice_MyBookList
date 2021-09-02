package com.example.mybooklist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mybooklist.database.BooksDatabase
import com.example.mybooklist.database.asDomainModel
import com.example.mybooklist.network.BookInfo
import com.example.mybooklist.network.MyApi
import com.example.mybooklist.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(private val database:BooksDatabase) {
    val books:LiveData<List<BookInfo>> = Transformations.map(database.bookDao.getBooks()){
        it.asDomainModel()
    }

    suspend fun refreshBooks(){
        withContext(Dispatchers.IO){
            val bookList =
                MyApi.retrofitService.getBooksInfo("KakaoAK ef14a58fbc02784bede6c21eec8664bb", "오").await()
            database.bookDao.insertAll(*bookList.asDatabaseModel())
        }
    }
}