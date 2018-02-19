package com.example.kirill.retrofittry.Activities;

import android.app.Application;

import com.example.kirill.retrofittry.Interfaces.API;
import com.example.kirill.retrofittry.Interfaces.GetCourses;
import com.example.kirill.retrofittry.Settings.Settings;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static API api;
    private static GetCourses allcourses;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Settings.POINT_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .client(client)
                .build();
        api = retrofit.create(API.class);//Создаем объект, при помощи которого будем выполнять запросы
        allcourses = retrofit.create(GetCourses.class);
    }

    public static API getApi() {
        return api;
    }
    public static GetCourses getCourses() {
        return allcourses;
    }
}

