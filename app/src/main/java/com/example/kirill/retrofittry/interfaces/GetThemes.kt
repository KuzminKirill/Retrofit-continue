package com.example.kirill.retrofittry.interfaces

import com.example.kirill.retrofittry.Parsers.Question
import com.example.kirill.retrofittry.Parsers.Theme
import com.example.kirill.retrofittry.Parsers.Themes
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetThemes {
    @GET("themes")
    abstract fun all(): Call<Themes>

    @GET("courses/{id}")
    abstract fun select(@Path("id") id: Int): Call<Theme>

    class Questions {

        @SerializedName("questions")
        @Expose
        var questions: List<Question>? = null

    }
}