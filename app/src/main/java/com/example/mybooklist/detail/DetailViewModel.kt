package com.example.mybooklist.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.mybooklist.network.BookInfo

class DetailViewModel(bookInfo: BookInfo, application: Application) : AndroidViewModel(application) {

    //private var bookInfo: BookInfo ?= null

    private val _selectedBook = MutableLiveData<BookInfo>()
    val selectedBook : LiveData<BookInfo>
        get() = _selectedBook
    init {
        _selectedBook.value = bookInfo
    }



//    fun setBookInfo(b: BookInfo){
//        this.bookInfo = b
//    }

}