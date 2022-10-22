package com.example.todaysschoolmeal

import retrofit2.http.GET
import retrofit2.http.Path
import java.time.LocalDate

interface MealApiService {
    @GET("api/high/{schoolcode}")
    suspend fun getMeal(@Path("schoolcode") schoolcode : String      ) : MealApiResult
}