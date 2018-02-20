package com.example.kirill.retrofittry.Activities

import android.app.Application
import com.example.kirill.retrofittry.Interfaces.API
import com.example.kirill.retrofittry.Interfaces.GetCourses
import com.example.kirill.retrofittry.Settings.Settings
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIKotlin : Application(){

    private object interfaces {
        lateinit var api: API
        lateinit var allcourses: GetCourses
        lateinit var retrofit: Retrofit
    }
/*
    private lateinit var api: API
    private lateinit var allcourses: GetCourses
    private lateinit var retrofit: Retrofit
*/
    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build()

        interfaces.retrofit = Retrofit.Builder()
                .baseUrl(Settings.POINT_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .client(client)
                .build()

        interfaces.api = interfaces.retrofit.create(API::class.java)//Создаем объект, при помощи которого будем выполнять запросы
        interfaces.allcourses = interfaces.retrofit.create(GetCourses::class.java)
    }
    companion object {
        fun getApi(): API {
            return interfaces.api
        }
        fun getCourses(): GetCourses {
            return interfaces.allcourses
        }
    }
}