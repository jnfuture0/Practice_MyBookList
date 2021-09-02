package com.example.mybooklist.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mybooklist.network.BookInfo

class DetailViewModel(bookInfo: BookInfo, app: Application) : AndroidViewModel(app) {
    private val _selectedBook = MutableLiveData<BookInfo>()
    val selectedBook : LiveData<BookInfo>
        get() = _selectedBook
    init {
        _selectedBook.value = bookInfo
    }

}