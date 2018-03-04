package com.example.kirill.retrofitTry.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.kirill.retrofitTry.Parsers.Courses
import com.example.kirill.retrofitTry.R
import com.example.kirill.retrofitTry.adapters.CoursesAdapter
import kotlinx.android.synthetic.main.get_courses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetCoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_courses)

        App.getCourses().all().enqueue(object : Callback<Courses> {
            override fun onResponse(call: Call<Courses>, response: Response<Courses>) {
                Log.d("Response code:", "===========" + response.code())

                Log.d("==Cookie==1===", "==="+response.raw().request().headers().get("Set-Cookie"))
                Log.d("==Cookie==2==", "==="+response.headers().get("Set-Cookie"))
                Log.d("==Content-Type====", "==="+response.headers().get("Content-Type"))
                val adapter = CoursesAdapter(this@GetCoursesActivity, response.body())
                lv.adapter = adapter
            }

            override fun onFailure(call: Call<Courses>, t: Throwable) {
                Log.e("coursesActivity", "server is not responding")
                Toast.makeText(applicationContext,
                        "Server is not responding", Toast.LENGTH_SHORT).show()
            }
        })
    }
}