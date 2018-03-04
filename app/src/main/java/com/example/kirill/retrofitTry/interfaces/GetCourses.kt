package com.example.kirill.retrofitTry.interfaces

import com.example.kirill.retrofitTry.Parsers.Course
import com.example.kirill.retrofitTry.Parsers.Courses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCourses {
    @GET("courses")
    fun all(): Call<Courses>

    @GET("courses/{id}")
    fun select(@Path("id") id: Int): Call<Course>
}