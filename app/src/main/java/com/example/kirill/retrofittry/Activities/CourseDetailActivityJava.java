package com.example.kirill.retrofittry.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import com.example.kirill.retrofittry.Interfaces.GetCourses;
import com.example.kirill.retrofittry.Interfaces.GetQuestions;
import com.example.kirill.retrofittry.Interfaces.GetTests;
import com.example.kirill.retrofittry.Interfaces.GetThemes;
import com.example.kirill.retrofittry.R;
import com.example.kirill.retrofittry.Settings.Settings;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CourseDetailActivityJava extends AppCompatActivity {

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

        TextView que = findViewById(R.id.questions);
        que.setText(getIntent().getExtras().getString("id"));
    }
}
