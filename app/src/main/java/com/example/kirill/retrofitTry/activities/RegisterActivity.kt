package com.example.kirill.retrofitTry.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

import com.example.kirill.retrofitTry.R
import com.example.kirill.retrofitTry.structures.RegistrationBody
import com.example.kirill.retrofitTry.structures.RegistrationResponse
import kotlinx.android.synthetic.main.register.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private fun registerUser(body: RegistrationBody) {
        App.getApi().registerUser(body).enqueue(object : Callback<RegistrationResponse> {

            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                if (response.isSuccessful) {
                    Log.e("Success", "it's worked")
                    App.setToken(response.toString())
                    val i = Intent(this@RegisterActivity, GetCoursesActivity::class.java)
                    startActivity(i)
                } else {
                    Log.e("error response", "error with token")
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Log.e("failure", "failure!!!", t)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        register_submit.setOnClickListener({
            val body = RegistrationBody(register_username.text.toString(),
                                        register_email.text.toString(),
                                        register_password.text.toString(),
                                        register_confirm.text.toString())
            registerUser(body)
        })

        val loginText = findViewById<TextView>(R.id.register_linkToLogin)
        loginText.setOnClickListener({
            val i = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(i)
        })
    }

}