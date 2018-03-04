package com.example.kirill.retrofitTry.interfaces

import com.example.kirill.retrofitTry.structures.LoginBody
import com.example.kirill.retrofitTry.structures.LoginResponse
import com.example.kirill.retrofitTry.structures.RegistrationBody
import com.example.kirill.retrofitTry.structures.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("rest-auth/registration/")
    fun registerUser(@Body registrationBody: RegistrationBody): Call<RegistrationResponse>

    @POST("/rest-auth/login/")
    fun loginUser(@Body loginBody: LoginBody): Call<LoginResponse>
}