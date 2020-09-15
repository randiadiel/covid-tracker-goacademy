package com.example.covidtrackergoacademy.main

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class Model {
    private val okHttpClient = OkHttpClient()

    fun getData(callback: Callback){
        val request: Request = Request.Builder().url("https://api.kawalcorona.com/indonesia/").build()

        okHttpClient.newCall(request).enqueue(callback)
    }
}