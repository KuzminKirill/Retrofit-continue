package com.example.kirill.retrofittry.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kirill.retrofittry.BuildConfig;
import com.example.kirill.retrofittry.Interfaces.API;
import com.example.kirill.retrofittry.R;
import com.example.kirill.retrofittry.Settings.Settings;
import com.example.kirill.retrofittry.Structures.RegistrationBody;
import com.example.kirill.retrofittry.Structures.RegistrationResponse;

import java.util.concurrent.TimeUnit;

import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private API api;
    private TextView name;
    private TextView email;
    private TextView password1;
    private TextView password2;


    public String authtoken = "Token ";

    private void RegisterUser(RegistrationBody body) {
        Call<RegistrationResponse> call = api.registerUser(body);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("sucsess", "it's worked");
                    authtoken = authtoken + response.toString();
                    Intent i = new Intent(MainActivity.this, TableCourseActivity.class);
                    startActivity(i);
                } else {
                    Log.e("error response", "error with token");
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.e("falue", "falue!!!", t);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Settings.POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(API.class);
        name = (TextView) findViewById(R.id.Name);
        email = (TextView) findViewById(R.id.email);
        password1 = (TextView) findViewById(R.id.password1);
        password2 = (TextView) findViewById(R.id.password2);

        Button registerbtn = (Button) findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationBody body = new RegistrationBody();
                body.username = name.getText().toString();
                body.email = email.getText().toString();
                body.password1 = password1.getText().toString();
                body.password2 = password2.getText().toString();
                RegisterUser(body);
            }
        });

        TextView logintext = (TextView) findViewById(R.id.loginurl);
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}


