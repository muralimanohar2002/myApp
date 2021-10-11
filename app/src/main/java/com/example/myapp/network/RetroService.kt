package com.example.myapp.network

import com.example.myapp.Models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("everything")
    suspend fun getDataFromApi(@Query("domains") query: String, @Query("apiKey") api: String): RecyclerList
}