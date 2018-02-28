package com.example.kirill.retrofittry.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast

import com.example.kirill.retrofittry.R
import com.example.kirill.retrofittry.Structures.RegistrationBody
import com.example.kirill.retrofittry.Structures.RegistrationResponse
import kotlinx.android.synthetic.main.register.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    var authtoken = "Token "

    private fun registerUser(body: RegistrationBody) {
        App.getApi().registerUser(body).enqueue(object : Callback<RegistrationResponse> {

            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_SHORT)
                    Log.e("sucsess", "it's worked")
                    authtoken += response.toString()
                    val i = Intent(this@RegisterActivity, GetCoursesActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this@RegisterActivity, "Invalid token", Toast.LENGTH_SHORT)
                    Log.e("error response", "error with token")
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Server is not responding", Toast.LENGTH_SHORT)
                Log.e("falue", "falue!!!", t)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        register_button.setOnClickListener({
            val body = RegistrationBody(Name.text.toString(),
                                        email.text.toString(),
                                        password1.text.toString(),
                                        password2.text.toString())
            registerUser(body)
        })

        val loginText = findViewById<TextView>(R.id.login_redirect)
        loginText.setOnClickListener({
            val i = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(i)
        })
    }

}