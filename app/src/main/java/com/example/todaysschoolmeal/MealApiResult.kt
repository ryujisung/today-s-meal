package com.example.todaysschoolmeal

data class MealApiResult(
    val server_message : List<String>,
    val menu : List<Meal>
)

data class Meal(
    val date : Int,
    val lunch : List<String>,
    val dinner : List<String>,
    val breakfast : List<String>,
)
