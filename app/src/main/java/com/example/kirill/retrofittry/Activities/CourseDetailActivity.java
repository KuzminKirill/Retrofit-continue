package com.example.kirill.retrofittry.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;


import com.example.kirill.retrofittry.Interfaces.GetCourses;
import com.example.kirill.retrofittry.Interfaces.GetQuestions;
import com.example.kirill.retrofittry.Interfaces.GetTests;
import com.example.kirill.retrofittry.Interfaces.GetThemes;
import com.example.kirill.retrofittry.Parsers.Question;
import com.example.kirill.retrofittry.R;
import com.example.kirill.retrofittry.Settings.Settings;

/**
 * Created by Kirill on 08.05.2017.
 */

public class CourseDetailActivity extends AppCompatActivity {


    private GetCourses getCourses;
    private GetQuestions getQuestions;
    private GetThemes getThemes;
    private GetTests getTests;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Settings.POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        getCourses = retrofit.create(GetCourses.class);
        getQuestions = retrofit.create(GetQuestions.class);
        getThemes = retrofit.create(GetThemes.class);
        getTests = retrofit.create(GetTests.class);

        loadQuestion(1);
    }

    private void loadQuestion(int id) {
        Call<Question> call = getQuestions.select(id);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                displayQuestion(response.body());
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("one course", "I got an error with one course", t);
            }
        });
    }

    private void displayQuestion(Question question) {
        if (question != null) {
            LinearLayout mainL = (LinearLayout) findViewById(R.id.detailLayout);

            LayoutParams viewParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);

            TextView txtV = new TextView(this);
            txtV.setText(question.getText());
            txtV.setTextSize(18);
            txtV.setPadding(10, 0, 0, 0);
            txtV.setTextColor(getResources().getColor(android.R.color.black));
            txtV.setLayoutParams(viewParams);
            mainL.addView(txtV);
        } else {

        }
    }
}
