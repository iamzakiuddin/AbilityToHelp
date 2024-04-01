package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtil {

    var BASE_URL = "https://synonyms-api.p.rapidapi.com/"

    private fun provideRetrofit(): Retrofit {
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
            this.level = HttpLoggingInterceptor.Level.HEADERS
        }
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor = intercepter).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideRepository(): Repository{
        val retrofit = provideRetrofit()
        val dataApi = retrofit
            .create(RestApi::class.java)
        return Repository(dataApi)
    }
}