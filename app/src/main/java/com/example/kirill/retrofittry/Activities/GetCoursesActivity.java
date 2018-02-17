package com.example.kirill.retrofittry.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    private TextView coursesTV;
    private GetCourses getCourses;
    private List<Course> courslist;

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
        coursesTV = findViewById(R.id.coursesTv);
        getCourses = retrofit.create(GetCourses.class);
        Log.e("coursesActivity", "retrofit done");
        //Button allBtn = findViewById(R.id.getallBtn);
        /*allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCourses();
            }
        });*/

        //Button oneBtn = findViewById(R.id.oneBtn);
        /*oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCourse(2);
            }
        });*/

       // loadCourses();

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
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Пора покормить кота!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });



        ///example data
       //ArrayList<Course> ex = new ArrayList<>();
       //for (int i =0;i<10;i++){
       //    Course q = new Course();
       //    q.setId(i);
       //    q.setDescription("description " + i);
       //    ex.add(q);
       //}
       //courses = new Courses();
       //courses.setCourses(ex);
       /////end example data


       //adapter = new com.example.kirill.retrofittry.Activities.Adapter(this,courses);
       //ListView lv = findViewById(R.id.lv);
       //lv.setAdapter((ListAdapter) adapter);
    }

    /*private Courses loadCourses() {
        Call<Courses> call = getCourses.all();
        call.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                courses = response.body();
               // displayCourses(courses);
                Log.e("all courses", "courses displayed");
            }
            @Override
            public void onFailure(Call<Courses> call, Throwable t) {
                Log.e("one course", "I got an error with all courses", t);
            }
        });
        return courses;
    }*/
/*
    private void displayCourses(Courses c) {
        if (c != null) {
            List<Course> courses = c.getCourses();
            String tmp = "";

            for (Course course : courses) {
                tmp += course.getId() + " | " + course.getDescription() + " | " + courses.size();
                coursesTV.setText(tmp);
            }
        } else {
            coursesTV.setText("Error to get Courses");
        }
    }*/
/*
    private void loadCourse(int id) {
        Call<Course> call = getCourses.select(id);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                displayCourse(response.body());
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.e("one course", "I got an error with one course", t);
            }
        });
    }*/

   /* private void displayCourse(Course course) {
        if (course != null) {
            String tmp = course.getId() + " | " + course.getDescription() + " |\n";
            coursesTV.setText(tmp);
        } else {
            coursesTV.setText("Error to get Course");
        }
    }*/
}
