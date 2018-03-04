package com.example.kirill.retrofitTry.interfaces

import com.example.kirill.retrofitTry.Parsers.Question
import com.example.kirill.retrofitTry.Parsers.Theme
import com.example.kirill.retrofitTry.Parsers.Themes
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetThemes {
    @GET("themes")
    fun all(): Call<Themes>

    @GET("courses/{id}")
    fun select(@Path("id") id: Int): Call<Theme>

    class Questions {

        @SerializedName("questions")
        @Expose
        var questions: List<Question>? = null

    }
}