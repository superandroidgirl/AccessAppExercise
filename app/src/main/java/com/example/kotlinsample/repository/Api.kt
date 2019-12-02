package com.example.kotlinsample.repository

import com.example.kotlinsample.model.ResultUser
import com.example.kotlinsample.model.UserDetail
import com.example.kotlinsample.model.UserInfo
import com.example.kotlinsample.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    /**
     * 取得使用者列表
     */
    @GET("users")
    suspend fun getUserList(@Query("since") since : String): Response<MutableList<UserInfo>>

    /**
     * 取得使用者明細
     */
    @GET("users/{name}")
    suspend fun getUserDetail(@Path("name") name : String): UserDetail
}