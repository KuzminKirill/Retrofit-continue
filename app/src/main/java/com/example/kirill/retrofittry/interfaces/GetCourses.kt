package com.example.kirill.retrofittry.interfaces

import com.example.kirill.retrofittry.Parsers.Course
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCourses {
    @GET("courses")
    fun all(): Call<List<Course>>

    @GET("courses/{id}")
    fun select(@Path("id") id: Int): Call<Course>
}