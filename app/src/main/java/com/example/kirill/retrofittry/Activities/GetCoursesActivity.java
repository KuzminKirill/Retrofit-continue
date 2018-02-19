package com.example.kirill.retrofittry.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kirill.retrofittry.Parsers.Course;
import com.example.kirill.retrofittry.Parsers.Courses;
import com.example.kirill.retrofittry.Interfaces.GetCourses;
import com.example.kirill.retrofittry.R;
import com.example.kirill.retrofittry.Settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCoursesActivity extends AppCompatActivity {

    private Adapter adapter;
    private Courses courses;
    private GetCourses getCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_courses);
        Log.e("coursesActivity", "start creating activity");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Log.e("coursesActivity", "http client done");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Settings.POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        getCourses = retrofit.create(GetCourses.class);
        Log.e("coursesActivity", "retrofit done");

        App.getCourses().all().enqueue(new Callback<List<Course>>(){
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                ArrayList<Course> courslist = new ArrayList<>();
                courslist.addAll(response.body());
                courses = new Courses();
                courses.setCourses(courslist);


                adapter = new com.example.kirill.retrofittry.Activities.Adapter(GetCoursesActivity.this, courses);
                ListView lv = findViewById(R.id.lv);
                lv.setAdapter((ListAdapter) adapter);
            }
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.e("coursesActivity", "server is not responding");
                Toast.makeText(getApplicationContext(),
                        "Server is not responding", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
