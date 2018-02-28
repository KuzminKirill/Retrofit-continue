package com.example.kirill.retrofittry.activities

import android.app.Application
import com.example.kirill.retrofittry.interfaces.*
import com.example.kirill.retrofittry.settings.Settings
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application(){

    private object interfaces {
        lateinit var api: API
        lateinit var allcourses: GetCourses
        lateinit var themes : GetThemes
        lateinit var questions : GetQuestions
        lateinit var tests : GetTests
    }
    private lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(Settings.POINT_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .client(client)
                .build()

        interfaces.api = retrofit.create(API::class.java)//Создаем объект, при помощи которого будем выполнять запросы
        interfaces.allcourses = retrofit.create(GetCourses::class.java)
        interfaces.themes = retrofit.create(GetThemes::class.java)
        interfaces.questions = retrofit.create(GetQuestions::class.java)
        interfaces.tests = retrofit.create((GetTests::class.java))
    }
    companion object {
        fun getApi(): API { return interfaces.api }
        fun getCourses(): GetCourses { return interfaces.allcourses }
        fun getThemes() : GetThemes { return interfaces.themes }
        fun getQuestions() : GetQuestions { return interfaces.questions }
        fun getTests() : GetTests { return interfaces.tests }
    }
}