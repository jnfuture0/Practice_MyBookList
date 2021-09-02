package com.example.mybooklist.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://dapi.kakao.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface MyApiService{


    @GET("v3/search/book")
    fun getBooksInfo(
        @Header("Authorization") authorization:String,
        @Query("query") query:String,
        @Query("target") target:String = "title"
    ): Deferred<MyBooksInfo>

}

object MyApi{
    val retrofitService : MyApiService by lazy{
        retrofit.create(MyApiService::class.java)
    }
}