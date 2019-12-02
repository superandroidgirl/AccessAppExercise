package com.example.kotlinsample.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object UserRepository {
    private fun getApiService(): Api {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply { addInterceptor(interceptor) }.build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    suspend fun getUserLists(id : String) = getApiService().getUserList(id)

    suspend fun getUserDetail(name : String) = getApiService().getUserDetail(name)

}