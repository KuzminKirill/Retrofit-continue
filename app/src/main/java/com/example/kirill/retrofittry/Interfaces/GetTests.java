package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Parsers.Test;
import com.example.kirill.retrofittry.Parsers.Tests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kirill on 08.05.2017.
 */

public interface GetTests {
    @GET("tests")
    Call<Tests> all();

    @GET("tests/{id}")
    Call<Test> select(@Path("id") int id);
}
