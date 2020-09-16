package com.example.covidtrackergoacademy.hotline.model

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class HotlineModel {
    private val okHttpClient = OkHttpClient()

    fun getData(callback: Callback) {
        val request : Request = Request.Builder()
            .url("https://bncc-corona-versus.firebaseio.com/v1/hotlines.json")
            .build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}