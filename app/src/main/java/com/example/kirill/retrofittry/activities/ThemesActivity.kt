package com.example.kirill.retrofittry.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kirill.retrofittry.Parsers.Themes
import com.example.kirill.retrofittry.R
import com.example.kirill.retrofittry.adapters.ThemesAdapter
import kotlinx.android.synthetic.main.themes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.themes)

        App.getThemes().all().enqueue(object : Callback<Themes> {
            override fun onResponse(call: Call<Themes>?, response: Response<Themes>) {
                val adapter = ThemesAdapter(this@ThemesActivity, response.body())
                lv.adapter = adapter
            }

            override fun onFailure(call: Call<Themes>?, t: Throwable?) {
                Log.e("themesActivity", "server is not responding")
                Toast.makeText(applicationContext,
                        "Server is not responding", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
