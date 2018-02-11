package com.example.kirill.retrofittry.Interfaces;

import com.example.kirill.retrofittry.Structures.LoginBody;
import com.example.kirill.retrofittry.Structures.LoginResponse;
import com.example.kirill.retrofittry.Structures.RegistrationBody;
import com.example.kirill.retrofittry.Structures.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Kirill on 19.04.2017.
 */

public interface API {
    @POST("rest-auth/registration/")
    Call<RegistrationResponse> registerUser(@Body RegistrationBody registrationBody);

    @POST("/rest-auth/login/")
    Call<LoginResponse> loginUser(@Body LoginBody loginBody);
}
