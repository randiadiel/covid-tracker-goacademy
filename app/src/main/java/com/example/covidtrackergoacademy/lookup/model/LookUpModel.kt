package com.example.covidtrackergoacademy.lookup.model

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class LookUpModel {
    private val okHttpClient = OkHttpClient()

    fun getData(callback : Callback) {
        val request : Request = Request
            .Builder()
            .url("https://api.kawalcorona.com/indonesia/provinsi/")
            .build()

        okHttpClient
            .newCall(request)
            .enqueue(callback)
    }
}