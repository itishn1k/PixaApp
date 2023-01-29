package com.example.pixabayapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImage(
        @Query("q") keyWord: String,
        @Query("key") key: String = "33167179-99b674204de75d745581a34d5",
    ): Call<PixaModel>
}