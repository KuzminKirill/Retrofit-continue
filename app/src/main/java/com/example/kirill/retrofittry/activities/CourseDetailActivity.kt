package com.example.kirill.retrofittry.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.kirill.retrofittry.R

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        val que = findViewById<TextView>(R.id.questions)
        que.text = intent.extras.getString("id")
    }
}