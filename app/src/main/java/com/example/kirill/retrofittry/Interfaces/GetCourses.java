package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Parsers.Course;
import com.example.kirill.retrofittry.Parsers.Courses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetCourses {
    @GET("courses")
    Call<List<Course>> all();

    @GET("courses/{id}")
    Call<Course> select(@Path("id") int id);
}
