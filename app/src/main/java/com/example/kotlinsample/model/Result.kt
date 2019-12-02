package com.example.kotlinsample.model

import com.google.gson.annotations.SerializedName

class ResultUser {
    @SerializedName("result")
    private var result: List<UserInfo>? = null

    fun getResult(): List<UserInfo>? {
        return result
    }

    fun setResult(result: List<UserInfo>) {
        this.result = result
    }
}