package com.example.kirill.retrofittry.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirill.retrofittry.Interfaces.API;
import com.example.kirill.retrofittry.Settings.Settings;
import com.example.kirill.retrofittry.Structures.LoginBody;
import com.example.kirill.retrofittry.Structures.LoginResponse;
import com.example.kirill.retrofittry.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private API api;
    private TextView name;
    private TextView password;
    public String authtoken = "Token ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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

        name = findViewById(R.id.Name);
        password = findViewById(R.id.Password);

        TextView registerText = findViewById(R.id.registerurl);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button loginBtn = findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBody body = new LoginBody();
                body.username = name.getText().toString();
                body.password = password.getText().toString();
                LoginUser(body);
            }
        });

    }

    private void LoginUser(LoginBody loginbody) {
        Call<LoginResponse> call = api.loginUser(loginbody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    Log.e("sucsess", "it's worked");
                    //authtoken = authtoken + response.toString();
                    Intent i = new Intent(LoginActivity.this, GetCoursesActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this,"Invalid token",Toast.LENGTH_SHORT).show();
                    Log.e("error response", "error with token");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Server is not responding",Toast.LENGTH_SHORT).show();
                Log.e("falue", "falue!!!", t);
            }
        });
    }


}
