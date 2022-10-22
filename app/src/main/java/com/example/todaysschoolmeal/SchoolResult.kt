package com.example.todaysschoolmeal
data class schoolApiResult(
    val status : Int,
    val schools : List<School>
)
data class School(
    val name : String,
    val code : String,
    val office : String,
    val level : Int,
    val address : String,
)