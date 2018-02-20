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
import com.example.kirill.retrofittry.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCoursesActivityJava extends AppCompatActivity {

    private Adapter adapter;
    private Courses courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_courses);

        App.getCourses().all().enqueue(new Callback<List<Course>>(){
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                ArrayList<Course> courslist = new ArrayList<>();
                courslist.addAll(response.body());
                courses = new Courses();
                courses.setCourses(courslist);


                adapter = new com.example.kirill.retrofittry.Activities.Adapter(GetCoursesActivityJava.this, courses);
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
