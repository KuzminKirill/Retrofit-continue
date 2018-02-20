package com.example.kirill.retrofittry.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.kirill.retrofittry.Parsers.Course
import com.example.kirill.retrofittry.Parsers.Courses
import com.example.kirill.retrofittry.R
import kotlinx.android.synthetic.main.get_courses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class GetCoursesActivityKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_courses)

        APIKotlin.getCourses().all().enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                val courslist = ArrayList<Course>()
                courslist.addAll(response.body())
                var courses = Courses()
                courses.courses = courslist
                var adapter = Adapter(this@GetCoursesActivityKotlin, courses)
                lv.adapter = adapter
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.e("coursesActivity", "server is not responding")
                Toast.makeText(applicationContext,
                        "Server is not responding", Toast.LENGTH_SHORT).show()
            }
        })
    }
}