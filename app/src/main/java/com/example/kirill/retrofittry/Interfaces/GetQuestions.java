package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Parsers.Question;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetQuestions {
    @GET("questions")
    Call<GetThemes.Questions> all();

    @GET("questions/{id}")
    Call<Question> select(@Path("id") int id);
}
