package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Parsers.Course;
import com.example.kirill.retrofittry.Parsers.Courses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kirill on 17.04.2017.
 */

public interface GetCourses {
    @GET("courses")
    Call<Courses> all();

    @GET("courses/{id}")
    Call<Course> select(@Path("id") int id);
}
