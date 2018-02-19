package com.example.kirill.retrofittry.Activities

import kotlinx.android.synthetic.main.login.*

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import java.util.concurrent.TimeUnit

import com.example.kirill.retrofittry.R
import com.example.kirill.retrofittry.Settings.Settings
import com.example.kirill.retrofittry.Structures.LoginBody
import com.example.kirill.retrofittry.Structures.LoginResponse

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivityKotlin : AppCompatActivity() {

    var authtoken = "Token "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
/*
        val client = OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(Settings.POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
*/
        val registerText = findViewById<TextView>(R.id.registerurl)
        registerText.setOnClickListener({
            val i = Intent(this@LoginActivityKotlin, RegisterActivityKotlin::class.java)
            startActivity(i)
        })

        val loginBtn = findViewById<Button>(R.id.loginbtn)
        loginBtn.setOnClickListener({
            val body = LoginBody()
            body.username = Name.text.toString()
            body.password = Password.text.toString()
            loginUser(body)
        })
    }

    private fun loginUser(loginbody: LoginBody) {
        App.getApi().loginUser(loginbody).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@LoginActivityKotlin, "Success", Toast.LENGTH_SHORT).show()
                    Log.e("success", "it's worked")
                    //authtoken = authtoken + response.toString();
                    val i = Intent(this@LoginActivityKotlin, GetCoursesActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this@LoginActivityKotlin, "Invalid token", Toast.LENGTH_SHORT).show()
                    Log.e("error response", "error with token")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivityKotlin, "Server is not responding", Toast.LENGTH_SHORT).show()
                Log.e("failure", "failure!!!", t)
            }
        })
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

}