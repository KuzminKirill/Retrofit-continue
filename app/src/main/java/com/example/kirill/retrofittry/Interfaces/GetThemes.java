package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Parsers.Question;
import com.example.kirill.retrofittry.Parsers.Theme;
import com.example.kirill.retrofittry.Parsers.Themes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sergey on 18.04.2017.
 */

public interface GetThemes {
    @GET("themes")
    Call<Themes> all();

    @GET("courses/{id}")
    Call<Theme> select(@Path("id") int id);

    class Questions {

        @SerializedName("questions")
        @Expose
        private List<Question> questions = null;

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

    }
}
