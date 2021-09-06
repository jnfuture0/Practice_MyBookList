package com.example.mybooklist.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybooklist.domain.model.BookInfoDomain

class DetailViewModel(bookInfo: BookInfoDomain) : ViewModel() {

    //private var bookInfo: BookInfo ?= null
    private val _selectedBook = MutableLiveData<BookInfoDomain>()
    val selectedBook: LiveData<BookInfoDomain>
        get() = _selectedBook

    init {
        _selectedBook.value = bookInfo


    }


//    fun setBookInfo(b: BookInfo){
//        this.bookInfo = b
//    }

}
// facade pattern
//interface Printer {
//   fun makePrinter()
//}
//
//class PrinterOkay(context: Context,): Printer {
//    override fun makePrinter() {
//        println("Hello")
//    }
//}
//
//class PrinterImpl(context: Context, st:String) : Printer {
//    override fun makePrinter() {
//        println("Making printer... $st")
//    }
//}
//
//
//
//@Singleton
//class Logger @Inject constructor(printer: Printer) {
//    fun print(msg: String){
//        printer.makePrinter()
//        println(msg)
//    }
//}