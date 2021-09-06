package com.example.mybooklist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooklist.datasource.database.dao.BookDao
import com.example.mybooklist.datasource.database.entity.asDomainList
import com.example.mybooklist.datasource.network.MyApiService
import com.example.mybooklist.datasource.network.dto.asDatabaseModel
import com.example.mybooklist.datasource.network.dto.asDomainList
import com.example.mybooklist.domain.model.BookInfoDomain
import com.example.mybooklist.ui.booklist.MyApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepository
@Inject
constructor(
    private val provideApiService: MyApiService,
    private val provideBookDao: BookDao
) {
    private val authorization = "KakaoAK ef14a58fbc02784bede6c21eec8664bb"

    private val _booksInfo = MutableLiveData<List<BookInfoDomain>>()
    val booksInfo: LiveData<List<BookInfoDomain>>
        get() = _booksInfo

    private val _status = MutableLiveData<MyApiStatus>()
    val status: LiveData<MyApiStatus>
        get() = _status

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    private var _isEnd = false

    //_page와 _query Repository에서 관리하는 것이 맞는가?
    private var _page = 1
    private var _query = "오"

    init {
        _isError.value = false
    }


    suspend fun setBooksList(query: String) {
        try {
            _status.value = MyApiStatus.LOADING
            val listResult = provideApiService.getBooksInfo(authorization, query, 1)
            _isError.value = false

            //for getMoreBooks
            _query = query
            _isEnd = listResult.meta.is_end
            _page = 1

            withContext(Dispatchers.IO) {
                provideBookDao.deleteAll()
                provideBookDao.insertAll(*listResult.asDatabaseModel()) //exception?
            }
            _booksInfo.value = listResult.documents.asDomainList()
            _status.value = MyApiStatus.DONE
        } catch (e: Throwable) {
            _isError.value = true
            withContext(Dispatchers.IO) {
                val databaseBooksInfo = provideBookDao.getBooks().asDomainList()
                _booksInfo.postValue(databaseBooksInfo)
                _status.postValue(MyApiStatus.DONE)
            }
        }
    }

    suspend fun getMoreBooks() {
        if (!_isEnd && _page <= 49) {
            _page += 1
            try {
                _status.value = MyApiStatus.LOADING
                val listResult = provideApiService.getBooksInfo(authorization, _query, _page)

                _status.value = MyApiStatus.DONE
                _isError.value = false

                _isEnd = listResult.meta.is_end

                withContext(Dispatchers.IO) {
                    provideBookDao.insertAll(*listResult.asDatabaseModel())
                    val databaseBooksInfo = provideBookDao.getBooks().asDomainList()
                    _booksInfo.postValue(databaseBooksInfo)
                }
            } catch (e: Throwable) {
                _isError.postValue(true)
                _status.postValue(MyApiStatus.DONE)
            }
        }
    }
}