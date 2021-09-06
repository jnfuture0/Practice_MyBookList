package com.example.mybooklist.datasource.network

import com.example.mybooklist.datasource.network.dto.MyBooksInfoDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface MyApiService{
    @GET("v3/search/book")
    suspend fun getBooksInfo(
        @Header("Authorization") authorization:String,
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("target") target:String = "title",
        @Query("sort") sort:String = "accuracy"
    ): MyBooksInfoDto
}


//private const val BASE_URL = "https://dapi.kakao.com/"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addCallAdapterFactory(CoroutineCallAdapterFactory())
//    .baseUrl(BASE_URL)
//    .build()
//
//object MyApi{
//    val retrofitService : MyApiService by lazy{
//        retrofit.create(MyApiService::class.java)
//    }
//}