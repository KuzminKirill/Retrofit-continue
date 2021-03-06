package com.example.kirill.retrofitTry.interfaces

import com.example.kirill.retrofitTry.Parsers.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetQuestions {
    @GET("questions")
    fun all(): Call<GetThemes.Questions>

    @GET("questions/{id}")
    fun select(@Path("id") id: Int): Call<Question>
}