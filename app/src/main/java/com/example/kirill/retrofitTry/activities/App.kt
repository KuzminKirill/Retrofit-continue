package com.example.kirill.retrofitTry.activities

import android.app.Application
import com.example.kirill.retrofitTry.interfaces.*
import com.example.kirill.retrofitTry.settings.Settings
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Cookie
import okhttp3.HttpUrl



class App : Application(){

    private object Interfaces {
        lateinit var m_token: String
        lateinit var m_api: API
        lateinit var m_allCourses: GetCourses
        lateinit var m_themes: GetThemes
        lateinit var m_questions: GetQuestions
        lateinit var m_tests: GetTests
    }
    private lateinit var retrofit: Retrofit

    fun createNonPersistentCookie(): Cookie {
        return Cookie.Builder()
                .domain("publicObject.com")
                .path("/")
                .name("cookie-name")
                .value("cookie-value")
                .httpOnly()
                .secure()
                .build()
    }


    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
                .cookieJar(object : CookieJar {
                    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {}

                    override fun loadForRequest(url: HttpUrl): List<Cookie> {
                        val oneCookie = ArrayList<Cookie>(1)
                        oneCookie.add(createNonPersistentCookie())
                        return oneCookie
                    }
                })
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(Settings.POINT_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .client(client)
                .build()

        Interfaces.m_api = retrofit.create(API::class.java)//Создаем объект, при помощи которого будем выполнять запросы
        Interfaces.m_allCourses = retrofit.create(GetCourses::class.java)
        Interfaces.m_themes = retrofit.create(GetThemes::class.java)
        Interfaces.m_questions = retrofit.create(GetQuestions::class.java)
        Interfaces.m_tests = retrofit.create((GetTests::class.java))
    }
    companion object {
        fun setToken(token : String) { Interfaces.m_token = "Token " + token }
        fun getToken() : String { return Interfaces.m_token }
        fun getApi(): API { return Interfaces.m_api }
        fun getCourses(): GetCourses { return Interfaces.m_allCourses }
        fun getThemes() : GetThemes { return Interfaces.m_themes }
        fun getQuestions() : GetQuestions { return Interfaces.m_questions }
        fun getTests() : GetTests { return Interfaces.m_tests }
    }
}