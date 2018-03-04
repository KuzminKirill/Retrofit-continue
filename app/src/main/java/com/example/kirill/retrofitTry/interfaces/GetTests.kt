package com.example.kirill.retrofitTry.interfaces

import com.example.kirill.retrofitTry.Parsers.Test
import com.example.kirill.retrofitTry.Parsers.Tests
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetTests {
    @GET("tests")
    fun all(): Call<Tests>

    @GET("tests/{id}")
    fun select(@Path("id") id: Int): Call<Test>
}