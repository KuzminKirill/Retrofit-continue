package com.example.kirill.retrofitTry.activities

import kotlinx.android.synthetic.main.login.*

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

import com.example.kirill.retrofitTry.R
import com.example.kirill.retrofitTry.structures.LoginBody
import com.example.kirill.retrofitTry.structures.LoginResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        login_linkToRegister.setOnClickListener({
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        })

        login_submit.setOnClickListener({
            val body = LoginBody(register_username.text.toString(), login_password.text.toString())
            loginUser(body)
        })
        startActivity(Intent(this@LoginActivity,GetCoursesActivity::class.java))
    }

    private fun loginUser(loginBody: LoginBody) {
        App.getApi().loginUser(loginBody).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                    Log.e("success", "it's worked")
                    App.setToken(response.toString())
                    val i = Intent(this@LoginActivity, GetCoursesActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(applicationContext, "Invalid token", Toast.LENGTH_SHORT).show()
                    Log.e("error response", "error with token")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Server is not responding", Toast.LENGTH_SHORT).show()
                Log.e("failure", "failure!!!", t)
            }
        })
    }
}