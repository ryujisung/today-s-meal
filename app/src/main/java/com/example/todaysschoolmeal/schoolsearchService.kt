package com.example.todaysschoolmeal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface schoolsearchService {
    @GET("api/school/{school}")
    suspend fun ApiService(@Path("school") school : String ): schoolApiResult
}