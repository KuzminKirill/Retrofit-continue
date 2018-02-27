package com.example.kirill.retrofittry.interfaces

import com.example.kirill.retrofittry.Parsers.Test
import com.example.kirill.retrofittry.Parsers.Tests
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetTests {
    @GET("tests")
    abstract fun all(): Call<Tests>

    @GET("tests/{id}")
    abstract fun select(@Path("id") id: Int): Call<Test>
}