package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Parsers.Test;
import com.example.kirill.retrofittry.Parsers.Tests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetTests {
    @GET("tests")
    Call<Tests> all();

    @GET("tests/{id}")
    Call<Test> select(@Path("id") int id);
}
