package com.example.mybooklist.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooklist.domain.model.BookInfoDomain
import com.example.mybooklist.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MyApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class BookListViewModel
@Inject
constructor(private val booksRepository: BooksRepository) : ViewModel() {

    private val _navigateToSelectedBook = MutableLiveData<BookInfoDomain>()
    val navigateToSelectedBook: LiveData<BookInfoDomain>
        get() = _navigateToSelectedBook

    init {
        viewModelScope.launch {
            booksRepository.setBooksList("오")
        }
    }

    val booksInfo = booksRepository.booksInfo
    val status = booksRepository.status
    val isError = booksRepository.isError

    fun searchWithQuery(query: String) {
        viewModelScope.launch {
            booksRepository.setBooksList(query)
        }
    }

    fun getMoreBooks() {
        viewModelScope.launch {
            booksRepository.getMoreBooks()
        }
    }

    fun displayBookDetails(bookInfo: BookInfoDomain) {
        _navigateToSelectedBook.value = bookInfo
    }

    fun displayBookDetailsComplete() {
        _navigateToSelectedBook.value = null
    }

//    private var job: Job?=null
//
//    val coroutineScope = CoroutineScope(Dispatchers.Default)
//    fun increment() {
//        job =  viewModelScope.launch {
////            var i=0
////            while (true) {
////                println("${i++}")
////                delay(1000)
////            }
//            try {
//                aaa()
//                bbb()
//            }catch(e: CancellationException){
//                println(e.message)
//            }
//        }

//        val time= System.nanoTime()
//        var deferredA: Deferred<Long>
//        var deferredb: Deferred<Long>
//        val ds = mutableListOf<Deferred<Long>>()
//        viewModelScope.launch(Dispatchers.Default) {
//
//            deferredA = async {
//                aaa()
//                println("a " + (System.nanoTime() - time))
//
//                System.nanoTime() - time
//            }
//            deferredb = async {
//                bbb()
//                println("b " + (System.nanoTime() - time))
//
//                (System.nanoTime() - time)
//            }
//
//            //println("a: " + ds.awaitAll())
//            println("b: " + deferredb.await())
//        }
//        coroutineScope.launch{
//            try {
//                delay(1000)
//                println("Hello")
//                delay(3000)
//                incrementNumber()
//                delay(1000)
//            }catch(e: CancellationException){
//                println("Cancelled")
//            }
//        }
//    }

//    private suspend fun incrementNumber(): Int{
//        var i=0
//        while(true){
//            println("i : ${i++}")
//            delay(1000)
//        }
//    }
//
//
//
//    suspend fun aaa(){
//        delay(1000)
//        println("aaa")
//    }
//    suspend fun bbb(){
//        delay(1000)
//        println("bbb")
//    }
//
//    fun cancel(){
//        viewModelScope.coroutineContext.cancelChildren()
//        coroutineScope.coroutineContext.cancelChildren()
//        //viewModelScope.coroutineContext.cancelChildren()
//        //job?.cancel()
//        println("Job: ${job?.isActive}")
//        println("VMS: ${viewModelScope.coroutineContext.isActive}")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//      //  coroutineScope.cancel()
//       // coroutineScope.coroutineContext.cancel()
//    }


}