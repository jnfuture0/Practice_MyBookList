package com.example.mybooklist.booklist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybooklist.network.BookInfo
import com.example.mybooklist.network.MyApi
import com.example.mybooklist.network.MyBooksInfo
import kotlinx.coroutines.*

enum class MyApiStatus { LOADING, ERROR, DONE}

class BookListViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _booksInfo = MutableLiveData<List<BookInfo>>()
    val booksInfo : LiveData<List<BookInfo>>
        get() = _booksInfo

    private val _status = MutableLiveData<MyApiStatus>()
    val status : LiveData<MyApiStatus>
        get() = _status

    private val _navigateToSelectedBook = MutableLiveData<BookInfo>()
    val navigateToSelectedBook : LiveData<BookInfo>
        get() = _navigateToSelectedBook

    private val _query = MutableLiveData<String>()
    val query : LiveData<String>
        get() = _query


    init {
        _query.value = "오"
        getBooks(_query.value)
    }

    private fun getBooks(query:String?){
        coroutineScope.launch {
            var getBooksInfo : Deferred<MyBooksInfo> = if(query.isNullOrEmpty()){
                MyApi.retrofitService.getBooksInfo("KakaoAK ef14a58fbc02784bede6c21eec8664bb", "오")
            }else{
                MyApi.retrofitService.getBooksInfo("KakaoAK ef14a58fbc02784bede6c21eec8664bb", query)
            }
            Log.i("BookListViewModel", "CheckPoint 1")

            try{
                _status.value = MyApiStatus.LOADING
                val listResult = getBooksInfo.await()
                _status.value = MyApiStatus.DONE
                _booksInfo.value = listResult.documents
                Log.i("BookListViewModel", "CheckPoint 2 ${listResult.documents[0].title}")
            }catch (e:Throwable){
                _status.value = MyApiStatus.ERROR
                _booksInfo.value = ArrayList()
                Log.e("BookListViewModel", e.message.toString() + e.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun searchWithQuery(query:String?){
        //query값을 바꿔줄 필요가 있나?
        _query.value = query
        getBooks(query)
    }

    fun displayBookDetails(bookInfo:BookInfo){
        _navigateToSelectedBook.value = bookInfo
    }

    fun displayBookDetailsComplete(){
        _navigateToSelectedBook.value = null
    }
}