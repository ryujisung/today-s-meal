package com.example.todaysschoolmeal

import retrofit2.Call
import retrofit2.http.GET

data class data(val status: String ,val schools: String)

interface schoolsearchService {
    @GET("http://jrady721.cafe24.com/api/school/")
    fun ApiService(): Call<data>
}