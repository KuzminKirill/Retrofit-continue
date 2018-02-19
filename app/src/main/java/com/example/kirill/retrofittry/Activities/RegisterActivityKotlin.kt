package com.example.kirill.retrofittry.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast

import com.example.kirill.retrofittry.R
import com.example.kirill.retrofittry.Settings.Settings
import com.example.kirill.retrofittry.Structures.RegistrationBody
import com.example.kirill.retrofittry.Structures.RegistrationResponse
import kotlinx.android.synthetic.main.register.*

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


class RegisterActivityKotlin : AppCompatActivity() {
    var authtoken = "Token "

    private fun registerUser(body: RegistrationBody) {
        App.getApi().registerUser(body).enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivityKotlin, "Success", Toast.LENGTH_SHORT)
                    Log.e("sucsess", "it's worked")
                    authtoken += response.toString()
                    val i = Intent(this@RegisterActivityKotlin, TableCourseActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this@RegisterActivityKotlin, "Invalid token", Toast.LENGTH_SHORT)
                    Log.e("error response", "error with token")
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivityKotlin, "Server is not responding", Toast.LENGTH_SHORT)
                Log.e("falue", "falue!!!", t)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

     /*   val client = OkHttpClient.Builder()
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
        register_button.setOnClickListener({
            val body = RegistrationBody()
            body.username = Name.text.toString()
            body.email = email.text.toString()
            body.password1 = password1.text.toString()
            body.password2 = password2.text.toString()
            registerUser(body)
        })

        val loginText = findViewById<TextView>(R.id.login_redirect)
        loginText.setOnClickListener({
            val i = Intent(this@RegisterActivityKotlin, LoginActivityJava::class.java)
            startActivity(i)
        })
    }

}