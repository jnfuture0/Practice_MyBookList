package com.example.mybooklist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooklist.booklist.MyApiStatus
import com.example.mybooklist.database.BooksDatabase
import com.example.mybooklist.database.asDomainModel
import com.example.mybooklist.database.getDatabase
import com.example.mybooklist.network.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(private val database:BooksDatabase) {

    private val authorization = "KakaoAK ef14a58fbc02784bede6c21eec8664bb"

    private val _booksInfo = MutableLiveData<List<BookInfo>>()
    val booksInfo : LiveData<List<BookInfo>>
    get() = _booksInfo

    private val _status = MutableLiveData<MyApiStatus>()
    val status : LiveData<MyApiStatus>
        get() = _status

    private val _isError = MutableLiveData<Boolean>()
    val isError : LiveData<Boolean>
        get() = _isError

    private var _isEnd = false
    //_page와 _query Repository에서 관리하는 것이 맞는가?
    private var _page = 1
    private var _query = "오"

    init {
        _isError.value = false
    }


    suspend fun setBooksList(query:String){
        withContext(Dispatchers.IO){
            try{
                _status.postValue(MyApiStatus.LOADING)
                val getBooksInfo = MyApi.retrofitService.getBooksInfo(authorization, query, 1)
                val listResult = getBooksInfo
                _isError.postValue(false)

                //for getMoreBooks
                _query = query
                _isEnd = listResult.meta.is_end
                _page = 1

                database.bookDao.deleteAll()
                database.bookDao.insertAll(*listResult.asDatabaseModel()) //exception?

                withContext(Dispatchers.Main) {
                    _booksInfo.value = listResult.documents
                }

                _booksInfo.postValue(listResult.documents)
            }catch (e:Throwable){
                _isError.postValue(true)
                val databaseBooksInfo = database.bookDao.getBooks().asDomainModel()
                _booksInfo.postValue(databaseBooksInfo)
            }finally {
                _status.postValue(MyApiStatus.DONE)
            }
        }
    }

    suspend fun getMoreBooks(){
        if(!_isEnd && _page<=49){
            _page += 1
            withContext(Dispatchers.IO){
                val getBooksInfo = MyApi.retrofitService.getBooksInfo(authorization, _query, _page)

                try{
                    _status.postValue(MyApiStatus.LOADING)
                    val listResult = getBooksInfo
                    _status.postValue(MyApiStatus.DONE)
                    _isError.postValue(false)

                    _isEnd = listResult.meta.is_end

                    database.bookDao.insertAll(*listResult.asDatabaseModel())
                    val databaseBooksInfo = database.bookDao.getBooks().asDomainModel()
                    _booksInfo.postValue(databaseBooksInfo)
                }catch (e:Throwable){
                    _isError.postValue(true)
                    _status.postValue(MyApiStatus.DONE)
                }
            }
        }
    }
}