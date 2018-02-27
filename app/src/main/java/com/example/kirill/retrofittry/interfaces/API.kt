package com.example.kirill.retrofittry.interfaces

import com.example.kirill.retrofittry.Structures.LoginBody
import com.example.kirill.retrofittry.Structures.LoginResponse
import com.example.kirill.retrofittry.Structures.RegistrationBody
import com.example.kirill.retrofittry.Structures.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by n.letov on 27.02.2018.
 */
interface API {
    @POST("rest-auth/registration/")
    fun registerUser(@Body registrationBody: RegistrationBody): Call<RegistrationResponse>

    @POST("/rest-auth/login/")
    fun loginUser(@Body loginBody: LoginBody): Call<LoginResponse>
}