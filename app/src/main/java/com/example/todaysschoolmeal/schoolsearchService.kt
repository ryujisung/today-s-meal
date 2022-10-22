package com.example.todaysschoolmeal

import retrofit2.Call
import retrofit2.http.GET


interface schoolsearchService {
    @GET("api/school/소프트")//소프트를 유동적으로 변경
    suspend fun ApiService(): schoolApiResult
}