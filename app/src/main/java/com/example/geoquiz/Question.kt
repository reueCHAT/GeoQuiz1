package com.example.geoquiz

data class Question(@SrtingRes val textResId: Int , var answer: Boolean,var count_of_answer: Int = 0,val picture:QuestionPicture)

annotation class SrtingRes
