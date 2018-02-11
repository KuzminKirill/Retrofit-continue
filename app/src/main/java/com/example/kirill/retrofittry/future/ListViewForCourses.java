package com.example.kirill.retrofittry.future;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kirill.retrofittry.Parsers.Course;
import com.example.kirill.retrofittry.Parsers.Courses;
import com.example.kirill.retrofittry.Interfaces.GetCourses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kirill on 05.05.2017.
 */

public class ListViewForCourses extends ListActivity {
    public static final String BASE_URL = "http://192.168.0.105:8000";
    private GetCourses getCourses;
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        getCourses = retrofit.create(GetCourses.class);

        loadString();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + "selected", Toast.LENGTH_LONG).show();
    }


    private void loadString() {
        Call<Courses> call = getCourses.all();
        call.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                Courses courses = response.body();
                getString(courses);
            }


            @Override
            public void onFailure(Call<Courses> call, Throwable t) {
                Log.e("one course", "I got an error with all courses", t);
            }
        });
    }

    public String[] getString(Courses c) {
        int k = 0;
        if (c != null) {
            List<Course> courses = c.getCourses();
            String[] values = new String[courses.size()];

            for (Course course : courses) {
                values[k] = course.getDescription();
                k++;
            }

        }
        return values;
    }
}

